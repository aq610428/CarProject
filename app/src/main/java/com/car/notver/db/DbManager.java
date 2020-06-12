package com.car.notver.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class DbManager {
    /**
     * 通过id查找制定数据
     *
     * @param clazz 指定类
     * @param id    条件id
     * @param <T>   类型
     * @return 返回满足条件的对象
     */
    public static <T> T findById(SQLiteDatabase db, Class<T> clazz, int id) {
        Cursor cursor = db.query(clazz.getSimpleName(), null, "id=" + id, null, null, null, null);
        List<T> list = getEntity(cursor, clazz);

        if (list != null && list.size() > 0) {
            return list.get(0);
        }

        return null;

    }


    /**
     * 删除记录一条记录
     *
     * @param id 需要删除的 id索引
     */
    public static void deleteById(SQLiteDatabase db, String tabName, String id) {
        db.delete(tabName, "id=" + id, null);
    }


    public static void deleteTable(SQLiteDatabase db, String tabName) {
        db.execSQL("delete from " + tabName);
    }


    /**
     * 更新一条记录
     *
     * @param clazz  类
     * @param values 更新对象
     * @param id     更新id索引
     */
    public static void updateById(SQLiteDatabase db, Class<?> clazz, ContentValues values, long id) {
        db.update(clazz.getSimpleName(), values, "id=" + id, null);
    }


    /**
     * 根据指定条件返回满足条件的记录
     *
     * @param clazz      类
     * @param select     条件语句 ：（"id>？"）
     * @param selectArgs 条件(new String[]{"0"}) 查询id=0的记录
     * @param <T>        类型
     * @return 返回满足条件的list集合
     */
    public <T> List<T> findByArgs(SQLiteDatabase db, Class<T> clazz, String select, String[] selectArgs) {
        Cursor cursor = db.query(clazz.getSimpleName(), null, select, selectArgs, null, null, null);
        return getEntity(cursor, clazz);
    }


    /**
     * 查询数据库中所有的数据
     *
     * @param clazz
     * @param <T>   以 List的形式返回数据库中所有数据
     * @return 返回list集合
     * @throws InvocationTargetException
     */
    public static <T> List<T> findAll(SQLiteDatabase db, Class<T> clazz, String tabName) {
        Cursor cursor = db.query(tabName, null, null, null, null, null, null);
        return getEntity(cursor, clazz);
    }


    /**
     * 得到建表语句
     *
     * @param clazz 指定类
     * @return sql语句
     */
    public static String getCreateTable(Class<?> clazz, String tabName) {
        StringBuilder sb = new StringBuilder();
        sb.append("create table ").append(tabName).append(" (id  INTEGER PRIMARY KEY AUTOINCREMENT, ");
        Field[] fields = clazz.getDeclaredFields();
        for (Field fd : fields) {
            String fieldName = fd.getName();
            String fieldType = fd.getType().getName();
            if (fieldName.equalsIgnoreCase("_id") || fieldName.equalsIgnoreCase("id")) {
                continue;
            } else {
                sb.append(fieldName).append(DBUtils.getColumnType(fieldType)).append(", ");
            }
        }
        int len = sb.length();
        sb.replace(len - 2, len, ")");
        return sb.toString();
    }

    /**
     * put value to ContentValues for Database
     *
     * @param values ContentValues object
     * @param fd     the Field
     * @param obj    the value
     */
    public static void putValues(ContentValues values, Field fd, Object obj) {
        Class<?> clazz = values.getClass();
        try {
            Object[] parameters = new Object[]{fd.getName(), fd.get(obj)};
            Class<?>[] parameterTypes = getParameterTypes(fd, fd.get(obj), parameters);
            Method method = clazz.getDeclaredMethod("put", parameterTypes);
            method.setAccessible(true);
            method.invoke(values, parameters);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 得到反射方法中的参数类型
     *
     * @param field
     * @param fieldValue
     * @param parameters
     * @return
     */
    public static Class<?>[] getParameterTypes(Field field, Object fieldValue, Object[] parameters) {
        Class<?>[] parameterTypes;
        if (DBUtils.isCharType(field)) {
            parameters[1] = String.valueOf(fieldValue);
            parameterTypes = new Class[]{String.class, String.class};
        } else {
            if (field.getType().isPrimitive()) {
                parameterTypes = new Class[]{String.class, DBUtils.getObjectType(field.getType())};
            } else if ("java.util.Date".equals(field.getType().getName())) {
                parameterTypes = new Class[]{String.class, Long.class};
            } else {
                parameterTypes = new Class[]{String.class, field.getType()};
            }
        }
        return parameterTypes;
    }


    /**
     * 插入一条数据
     *
     * @param obj
     * @return 返回-1代表插入数据库失败，否则成功
     * @throws
     */
    public static long insert(SQLiteDatabase db, Object obj, String tabName) {
        Class<?> modeClass = obj.getClass();
        Field[] fields = modeClass.getDeclaredFields();
        ContentValues values = new ContentValues();
        for (Field fd : fields) {
            fd.setAccessible(true);
            String fieldName = fd.getName();
            //剔除主键id值得保存，由于框架默认设置id为主键自动增长
            if (fieldName.equalsIgnoreCase("id") || fieldName.equalsIgnoreCase("_id")) {
                continue;
            }
            putValues(values, fd, obj);
        }
        return db.insert(tabName, null, values);
    }

    /**
     * 从数据库得到实体类
     *
     * @param cursor
     * @param bean
     * @param <T>
     * @return
     */
    public static <T> List<T> getEntity(Cursor cursor, Class<T> bean) {
        List<T> list = new ArrayList<>();
        try {
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    Field[] fields = bean.getDeclaredFields();
                    T modeClass = bean.newInstance();
                    for (Field field : fields) {
                        Class<?> cursorClass = cursor.getClass();
                        String columnMethodName = DBUtils.getColumnMethodName(field.getType());
                        Method cursorMethod = cursorClass.getMethod(columnMethodName, int.class);
                        Object value = cursorMethod.invoke(cursor, cursor.getColumnIndex(field.getName()));

                        if (field.getType() == boolean.class || field.getType() == Boolean.class) {
                            if ("0".equals(String.valueOf(value))) {
                                value = false;
                            } else if ("1".equals(String.valueOf(value))) {
                                value = true;
                            }
                        } else if (field.getType() == char.class || field.getType() == Character.class) {
                            value = ((String) value).charAt(0);
                        } else if (field.getType() == Date.class) {
                            long date = (Long) value;
                            if (date <= 0) {
                                value = null;
                            } else {
                                value = new Date(date);
                            }
                        }
                        String methodName = DBUtils.makeSetterMethodName(field);
                        Method method = bean.getDeclaredMethod(methodName, field.getType());
                        method.invoke(modeClass, value);
                    }
                    list.add(modeClass);
                } while (cursor.moveToNext());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return list;
    }

}
