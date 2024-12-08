package kadai_010;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Scores_Chapter10 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		Connection con = null;
		//Statement statement = null;
		PreparedStatement statementUpdate = null;
		Statement statementSort = null;
		String sql = null;
		
        
        try {
            // データベースに接続
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/challenge_java",
                "root",
                ""
            );

            System.out.println("データベース接続成功"+con);
        
            //### scoresテーブル内の点数データを更新する ###
	        sql = "UPDATE scores SET score_math=?, score_english=? WHERE name LIKE ? AND CHAR_LENGTH(name)=? AND score_math=0 AND score_english=0";
	        statementUpdate = con.prepareStatement(sql);
	        
	        System.out.println("レコード更新を実行します");
	        //System.out.println(statementUpdate.toString());
	        
	        String serchName = "%武者小路勇気%";
	        statementUpdate.setInt(1, 95);
	        statementUpdate.setInt(2, 80);
	        statementUpdate.setString(3, serchName);
	        statementUpdate.setInt(4, serchName.length()+2-2);
	        
	        //　SQLクエリを実行（DBMSに送信）
	        //System.out.println(statementUpdate.toString());
	        //int x = 1/0;
	        int rowCnt = statementUpdate.executeUpdate();
	        System.out.println(rowCnt + "件のレコードが更新されました");
	        
	        //statement = null;
	        //System.gc();
	        
	        //### scoresテーブルのレコードを取得し、点数順に並べ替える ###
	        statementSort = con.createStatement();
	        sql = "SELECT `id`, `name`, `score_math`, `score_english` FROM `scores` ORDER BY `score_math` DESC, `score_english` DESC";
	        ResultSet result = statementSort.executeQuery(sql);
	        System.out.println("数学・英語の点数が高い順に並べ替えました");
	        
	        int i=1;
	        while (result.next()) {
	        	System.out.println(i + "件目：生徒ID=" + result.getInt("id") + "／氏名=" + result.getString("name") + "／数学=" + result.getInt("score_math") + "／英語=" + result.getInt("score_english"));
	        	i++;
	        }
	        
            // データベース接続を解除
            con.close();

        } catch(SQLException e) {
	        System.out.println("エラー発生：" + e.getMessage());
	    } finally {
	        // 使用したオブジェクトを解放
	        if( statementUpdate != null ) {
	            try { statementUpdate.close(); } catch(SQLException ignore) {}
	        }
	        if( statementSort != null ) {
	            try { statementSort.close(); } catch(SQLException ignore) {}
	        }
	        if( con != null ) {
	            try { con.close(); } catch(SQLException ignore) {}
	        }
	    }
        

	}

}
