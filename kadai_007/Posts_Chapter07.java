package kadai_007;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;//Arraysを使用する
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class _Posts_Chapter07 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		
		Connection con = null;
        //Statement statement = null;
		PreparedStatement statement = null;
        
        final List<String> val1 = Collections.unmodifiableList(Arrays.asList("1003", "1002", "1003", "1001", "1002"));
        final List<String> val2 = Collections.unmodifiableList(Arrays.asList("2023-02-08", "2023-02-08", "2023-02-09", "2023-02-09", "2023-02-10"));
        final List<String> val3 = Collections.unmodifiableList(Arrays.asList("昨日の夜は徹夜でした・・	", "お疲れ様です！", "今日も頑張ります！", "無理は禁物ですよ！", "明日から連休ですね！"));
        final List<String> val4 = Collections.unmodifiableList(Arrays.asList("13", "12", "18", "17", "20"));

        try {
            // データベースに接続
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/challenge_java",
                "root",
                ""
            );

            System.out.println("データベース接続成功："+con);
		
	        String sql = "INSERT INTO posts (user_id, posted_at, post_content, likes) VALUES (?, ?, ?, ?);";
	        statement = con.prepareStatement(sql);
	        
	        System.out.println("レコード追加を実行します");
	        int rowCnt[];
	        for(int i=0; i<5; i++) {
	        	statement.setString(1, val1.get(i));
	        	statement.setString(2, val2.get(i));
	        	statement.setString(3, val3.get(i));
	        	statement.setString(4, val4.get(i));
	        	statement.addBatch();        	
	        }

	        //　SQLクエリを実行（DBMSに送信）
	        rowCnt = statement.executeBatch();
	        System.out.println( rowCnt.length + "件のレコードが追加されました");
	        
	        sql = "SELECT user_id, posted_at, post_content, likes FROM posts WHERE user_id=1002;";
	        ResultSet result = statement.executeQuery(sql);
	        System.out.println("ユーザーIDが1002のレコードを検索しました");
	        //result.last();
	        //int number_of_row = result.getRow();
	        int i=1;
	        while (result.next()) {
	        	Date postedAt = result.getDate("posted_at");
	        	System.out.println(i + "件目：投稿日時=" + postedAt.toString() + "／投稿内容=" + result.getString("post_content") + "／いいね数=" + result.getInt("likes"));
	        	i++;
	        }
	        
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

