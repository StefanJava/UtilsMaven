package com.github.flyinghe.tools;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * 该类用于生成验证码图片， 注意该类实例化对象的成员参数的值由构造函数决定,且此后不可更改，
 * 实例化该类时将会自动根据默认参数或者构造函数中设置的参数生成一张验证码图片，可调用相关函数获取该图片
 * 
 * @author Flying
 * 
 */
public class VerificationCodeImage {
	private int w;
	private int h;
	private int textLength;
	private int fontBasicSize;
	private int fontSizeRange;
	private String[] fontNames = null;
	private String codes = null;
	private String text = null;
	private BufferedImage image = null;

	/**
	 * 默认构造函数 参数 w = 70 , h = 35 , textLength = 4, fontNames={ "宋体", "华文楷体",
	 * "黑体","微软雅黑", "楷体_GB2312" }, fontBasicSize = 24; fontSizeRange = 5;
	 * codes="23456789abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ";
	 * image根据默认参数自动生成一张图片
	 */
	public VerificationCodeImage() {
		super();
		this.w = 70;
		this.h = 35;
		this.textLength = 4;
		this.fontBasicSize = 24;
		this.fontSizeRange = 5;
		// {"宋体", "华文楷体", "黑体", "华文新魏", "华文隶书", "微软雅黑", "楷体_GB2312"}
		String[] fontNames = { "宋体", "华文楷体", "黑体", "微软雅黑", "楷体_GB2312" };
		this.fontNames = fontNames;
		this.codes = "23456789abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ";
		this.createImage();
	}

	/**
	 * 
	 * fontNames={ "宋体", "华文楷体", "黑体", "微软雅黑", "楷体_GB2312" },
	 * codes="23456789abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ",
	 * image根据构造参数和默认参数自动生成一张图片
	 * 
	 * @param w
	 *            图片宽
	 * @param h
	 *            图片高
	 * @param textLength
	 *            验证码字符串长度
	 * @param fontBasicSize
	 *            字体大小的最低值
	 * @param fontSizeRange
	 *            字体大小的浮动范围
	 */
	public VerificationCodeImage(int w, int h, int textLength,
			int fontBasicSize, int fontSizeRange) {
		super();
		this.w = w;
		this.h = h;
		this.textLength = textLength;
		this.fontBasicSize = fontBasicSize;
		this.fontSizeRange = fontSizeRange;
		// {"宋体", "华文楷体", "黑体", "华文新魏", "华文隶书", "微软雅黑", "楷体_GB2312"}
		String[] fontNames = { "宋体", "华文楷体", "黑体", "微软雅黑", "楷体_GB2312" };
		this.fontNames = fontNames;
		this.codes = "23456789abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ";
		this.createImage();
	}

	/**
	 * 根据构造参数自动生成一张验证码图片
	 * 
	 * @param w
	 *            图片宽
	 * @param h
	 *            图片高
	 * @param textLength
	 *            验证码字符串长度
	 * @param fontBasicSize
	 *            字体大小的最低值
	 * @param fontSizeRange
	 *            字体大小的浮动范围
	 * @param fontNames
	 *            指定验证码中字符字体类型，由字符类型名称组成的一个字符串数组
	 * @param codes
	 *            一个字符数组，生成的验证码中的字符从该字符在数组中获取,字符必须为英文状态下的字符
	 */
	public VerificationCodeImage(int w, int h, int textLength,
			int fontBasicSize, int fontSizeRange, String[] fontNames,
			String codes) {
		super();
		this.w = w;
		this.h = h;
		this.textLength = textLength;
		this.fontBasicSize = fontBasicSize;
		this.fontSizeRange = fontSizeRange;
		this.fontNames = fontNames;
		this.codes = codes;
		this.createImage();
	}

	/**
	 * 随机生成一种颜色
	 * 
	 * @return 返回随机生成的颜色
	 */
	private Color randomColor() {
		Random r = new Random();
		int red = r.nextInt(150);
		int green = r.nextInt(150);
		int blue = r.nextInt(150);
		return new Color(red, green, blue);
	}

	/**
	 * 随机生成一种字体
	 * 
	 * @return 返回随机生成的字体
	 */
	private Font randomFont() {
		Random r = new Random();
		int index = r.nextInt(this.fontNames.length);
		String fontName = this.fontNames[index];
		int style = r.nextInt(4);
		int size = r.nextInt(this.fontSizeRange) + this.fontBasicSize;
		return new Font(fontName, style, size);
	}

	/**
	 * 随机像图片中画短直线，短直线数目由textLength值决定
	 * 
	 * @param image
	 *            被画直线的图片
	 */
	private void drawLine(BufferedImage image) {
		Random r = new Random();
		Graphics2D g2 = (Graphics2D) image.getGraphics();
		for (int i = 0; i < this.textLength; i++) {
			int x1 = r.nextInt(w);
			int y1 = r.nextInt(h);
			int x2 = r.nextInt(w);
			int y2 = r.nextInt(h);
			g2.setStroke(new BasicStroke(1.5F));
			g2.setColor(this.randomColor());
			g2.drawLine(x1, y1, x2, y2);
		}
	}

	/**
	 * 随机返回codes字符串中的一个字符
	 * 
	 * @return 随机返回codes字符串中的一个字符
	 */
	private char randomChar() {
		Random r = new Random();
		int index = r.nextInt(codes.length());
		return codes.charAt(index);
	}

	/**
	 * 根据图片参数随机生成一张验证码图片
	 */
	private void createImage() {
		Random r = new Random();
		BufferedImage image = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = (Graphics2D) image.getGraphics();
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, w, h);
		// 记录生成的文本
		StringBuilder sb = new StringBuilder();
		// 向图片中画指定个数字符
		for (int i = 0; i < this.textLength; i++) {
			String s = randomChar() + "";
			sb.append(s);
			int a = this.w / this.textLength;
			int b = a * i + a * 2 / 3;
			// 字符x坐标在[a*i,b]范围内
			int x = r.nextInt(b - a * i + 1) + a * i;
			g2.setFont(randomFont());
			g2.setColor(randomColor());
			g2.drawString(s, x, this.h * 9 / 10);
		}
		drawLine(image);

		// 为对象参数赋值
		this.text = sb.toString();
		this.image = image;
	}

	/**
	 * 根据构造该对象时设置的参数重新随机生成一张验证码图片
	 */
	public void changeImage() {
		this.createImage();
	}

	/**
	 * 将指定的图片输出到指定位置
	 * 
	 * @param image
	 *            指定图片
	 * @param out
	 *            输出位置
	 * @throws IOException
	 */
	public static void output(BufferedImage image, OutputStream out)
			throws IOException {
		ImageIO.write(image, "JPEG", out);
	}

	/**
	 * 获取验证码图片的宽
	 * 
	 * @return 返回验证码图片的宽
	 */
	public int getW() {
		return w;
	}

	/**
	 * 获取验证码图片的高
	 * 
	 * @return 返回验证码图片的高
	 */
	public int getH() {
		return h;
	}

	/**
	 * 获取验证码图片中的文本长度
	 * 
	 * @return 返回验证码图片中的文本长度
	 */
	public int getTextLength() {
		return textLength;
	}

	/**
	 * 获取验证码图片中的字体大小最低值
	 * 
	 * @return 返回验证码图片中的字体大小最低值
	 */
	public int getFontBasicSize() {
		return fontBasicSize;
	}

	/**
	 * 获取验证码图片中的字体大小浮动范围
	 * 
	 * @return 返回图片宽
	 */
	public int getFontSizeRange() {
		return fontSizeRange;
	}

	/**
	 * 获取验证码图片中的文本字体类型取自于的字体集合
	 * 
	 * @return 返回验证码图片中的文本字体类型取自于的字体集合
	 */
	public String[] getFontNames() {
		return fontNames;
	}

	/**
	 * 获取验证码图片中的字符取自于指定的字符串
	 * 
	 * @return 返回验证码图片中的字符取自于指定的字符串
	 */
	public String getCodes() {
		return codes;
	}

	/**
	 * 获取验证码图片中的文本信息
	 * 
	 * @return 返回验证码图片中的文本信息
	 */
	public String getText() {
		return text;
	}

	/**
	 * 获取验证码图片
	 * 
	 * @return 返回验证码图片
	 */
	public BufferedImage getImage() {
		return image;
	}

}
