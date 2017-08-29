package com.github.flyinghe.depdcy;

import com.github.flyinghe.tools.XLSReader;
import com.github.flyinghe.tools.XLSXReader;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by FlyingHe on 2017/8/9.
 * 此接口为{@link XLSReader}和{@link XLSXReader}提供回调函数
 */
public interface ExcelWriterHandler {
    /**
     * 此函数作为{@link XLSReader}和{@link XLSXReader}读取Excel文件工具类的回调函数
     *
     * @param datas datas被清空前调用此函数时的数据行数据，
     *              在limit &gt;0的情况下datas.size()一般等于limit
     *              (在datas未达到指定限制而文件数据已经完全读取完毕的情况下datas.size()会小于limit),
     *              注意：若没有设置limit(即limit &lt;=0的情况下)，不会调用回调函数，
     *              此时你应该使用类似于{@link XLSXReader#readExcelToMapList(File)}等等
     *              不需要提供回调函数的静态函数来做处理
     * @see XLSReader#readExcelToMapList(File)
     * @see XLSReader#readExcelToMapList(File, Integer)
     * @see XLSReader#readExcel(File)
     * @see XLSReader#readExcel(File, Integer)
     */
    public boolean callback(List<Map<String, Object>> datas) throws Exception;
}
