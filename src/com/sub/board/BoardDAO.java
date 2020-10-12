package com.sub.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.sub.util.DBConnector;

public class BoardDAO {
	
private DBConnector dbConnector;
	
	public BoardDAO() {
		dbConnector = new DBConnector();
	}
	
	//select list
	public ArrayList<BoardDTO> boardList() throws Exception{
		
		ArrayList<BoardDTO> ar = new ArrayList<>();
		
		Connection con = dbConnector.getConnect();
		
		//4.SQL문 생성
		String sql = "select * from board order by num asc";
				
		//5. 미리전송
		PreparedStatement st = con.prepareStatement(sql);
				
		//7. 최종전송 후 결과처리
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			
			BoardDTO boardDTO = new BoardDTO();
			boardDTO.setNum(rs.getLong("num"));
			boardDTO.setTitle(rs.getString("title"));
			boardDTO.setWriter(rs.getString("writer"));
			boardDTO.setContents(rs.getString("contents"));
			boardDTO.setRegDate(rs.getDate("regDate"));
			boardDTO.setHit(rs.getLong("hit"));
			ar.add(boardDTO);
		}
		
		rs.close();
		st.close();
		con.close();
		
		return ar;
		
	}

}
