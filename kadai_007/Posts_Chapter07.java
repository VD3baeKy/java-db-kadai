package kadai_007;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Posts_Chapter07 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		
		Connection con = null;
        Statement statement = null;
        
        try {
            // データベースに接続
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/challenge_java",
                "root",
                ""
            );

            System.out.println("データベース接続成功");
            System.out.println(con);
	        
	        statement = con.createStatement();
		
	        String sql = """
	                     CREATE TABLE posts (
	                       post_id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	                       user_id INT(11) NOT NULL,
	                       posted_at DATE NOT NULL,
	                       post_content VARCHAR(255) NOT NULL,
	                       likes INT(11) DEFAULT 0
	                     );
	                     """;
	
	        //　SQLクエリを実行（DBMSに送信）
	        int rowCnt = statement.executeUpdate(sql);
	        System.out.println("テーブルを作成:rowCnt=" + rowCnt );
	        
            // データベース接続を解除
            con.close();

        } catch(SQLException e) {
	        System.out.println("エラー発生：" + e.getMessage());
	    } finally {
	        // 使用したオブジェクトを解放
	        if( statement != null ) {
	            try { statement.close(); } catch(SQLException ignore) {}
	        }
	        if( con != null ) {
	            try { con.close(); } catch(SQLException ignore) {}
	        }
	    }


	}

}
