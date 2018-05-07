package ec;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.BuyDataBeans;
import beans.ItemDataBeans;
import dao.BuyDAO;
import dao.BuyDetailDAO;

/**
 * 購入履歴画面
 * @author d-yamaguchi
 *
 */
@WebServlet("/UserBuyHistoryDetail")
public class UserBuyHistoryDetail extends HttpServlet {   //ユーザ詳細画面の設定
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

	try {
		int buyId = Integer.parseInt(request.getParameter("buy_id"));  //ﾘｸｴｽﾄﾊﾟﾗﾒｰﾀ。userdata.jspのﾘﾝｸからbuyidを受け取る。
		BuyDataBeans hBdb = BuyDAO.getBuyDataBeansByBuyId(buyId);
		ArrayList<ItemDataBeans> historyList = BuyDetailDAO.getItemDataBeansListByBuyId(buyId);

		session.setAttribute("hBdb", hBdb);
		session.setAttribute("historyList", historyList);

		request.getRequestDispatcher(EcHelper.USER_BUY_HISTORY_DETAIL_PAGE).forward(request, response);

		}catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}
}
