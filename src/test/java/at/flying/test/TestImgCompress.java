package at.flying.test;

import at.flying.tools.ImgUtils;
import org.junit.Test;

/**
 * Created by FlyingHe on 2016/12/5.
 */
public class TestImgCompress {
    @Test
    public void test1() {
        String srcFile = "C:\\Users\\FlyingHe\\Desktop\\高达一亿像素的中国地图 极适合做桌面.jpg";
        String desFile = "C:\\Users\\FlyingHe\\Desktop\\11.jpg";
        String desFile1 = "C:\\Users\\FlyingHe\\Desktop\\12.jpg";
        ImgUtils.imgCompressByScale(srcFile, desFile, 500, ImgUtils.WIDTH, -0.9f);
        ImgUtils.imgCompressByWH(srcFile, desFile1, 600, 500, 5.6f);
    }
}
