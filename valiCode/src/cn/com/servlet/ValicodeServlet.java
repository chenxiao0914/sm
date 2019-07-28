package cn.com.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/valiCode")
public class ValicodeServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BufferedImage image = new BufferedImage(200, 100, BufferedImage.TYPE_INT_RGB);
		
		Graphics2D gra = image.createGraphics();
		gra.setColor(Color.white);
		gra.fillRect(0, 0, 200, 100);
		
		List<Integer> rl = new ArrayList<Integer>();
		Random ran = new Random();
		for(int i=0;i<4;i++) {
			rl.add(ran.nextInt(10));
		}
		//设置字体
		gra.setColor(Color.black);
		Color[] colors = new Color[]{Color.black,Color.blue,Color.gray,Color.red,Color.green};
		gra.setFont(new Font("宋体",Font.BOLD|Font.ITALIC,40));
		for (int i = 0; i < rl.size(); i++) {
			gra.setColor(colors[ran.nextInt(colors.length)]);
			gra.drawString(rl.get(i)+"", 40*i, 70+(ran.nextInt(21)-10));
		}
		//划横线
		for (int i = 0; i < 4; i++) {
			gra.setColor(colors[ran.nextInt(colors.length)]);
			gra.drawLine(0, ran.nextInt(101), 200, ran.nextInt(101));
		}
		
		ServletOutputStream outputStream = resp.getOutputStream();
		ImageIO.write(image, "jpg", outputStream);
		
		//把验证码放到session中
		HttpSession session = req.getSession();
		session.setAttribute("valiCode", ""+rl.get(0)+rl.get(1)+rl.get(2)+rl.get(3));
	}
}
