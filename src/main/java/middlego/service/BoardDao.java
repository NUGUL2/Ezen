package middlego.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import middlego.dbconn.Dbconn;
import middlego.domain.BoardVo;
import middlego.domain.SearchCriteria;


public class BoardDao {

	private Connection conn;
	private PreparedStatement pstmt;
	
	public BoardDao() {
		Dbconn db = new Dbconn();
		this.conn = db.getConnection();
	}

	public int insertBoard(String subject, String content, String writer, String ip, int midx, String fileName, int fileSize){
		int value=0;
		String sql = "insert into MG_BOARD(BIDX,SUBjECT,CONTENT,WRITER,IP,MIDX,FILENAME,FILESIZE,"
				+ "ORIGINBIDX,LEVEL_,DEPTH)"
				+ "values(BIDX_MG_SEQ.nextval,?,?,?,?,?,?,?,BIDX_MG_SEQ.nextval,0,0)";
		
		//PreparedStatement : 변수를 직접 넣지 않고 메소드를 통해 집어넣게 처리하는 방식
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, subject);
			pstmt.setString(2, content);
			pstmt.setString(3, writer);
			pstmt.setString(4, ip); 
			pstmt.setInt(5,midx);
			pstmt.setString(6, fileName);
			pstmt.setInt(7, fileSize);
			value = pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return value;	
	}


	//insert 
	public ArrayList<BoardVo> boardSelectAll(SearchCriteria scri) {
		ArrayList<BoardVo> alist = new ArrayList<BoardVo>();
		ResultSet rs = null;
		String str = "";
		
		if (scri.getSearchType().equals("subject")) {
			 str = "and subject like ?";
		}else {
			str = "and writer like ?";
		}
		
		
		String sql = "SELECT * FROM ("
				+"SELECT ROWNUM AS rnum, A.* FROM ("
				+" select * from MG_BOARD where delyn = 'N' "+str+" order by originbidx desc, depth asc)A"	
				+")B WHERE rnum BETWEEN ? AND ?";
		try{
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, "%"+scri.getKeyword()+"%");
		pstmt.setInt(2, (scri.getPage()-1)*15+1);
		pstmt.setInt(3, scri.getPage()*15);
		rs = pstmt.executeQuery();
		//rs.next() 다음값이 존재하면 true이고 그 행으로 커서가 이동하는 메소드
		
		while(rs.next()){
			//반복할때마다 객체 생성한다
			BoardVo bv = new BoardVo();
			
			bv.setBidx(rs.getInt("Bidx"));
			bv.setSubject(rs.getString("Subject"));
			bv.setWriter(rs.getString("Writer"));
			bv.setWriteday(rs.getString("Writeday"));
			bv.setLevel_(rs.getInt("level_"));
			bv.setFilename(rs.getString("filename"));
			bv.setFilesize(rs.getInt("filesize"));
			alist.add(bv);	
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		return alist;
	}

	
	public BoardVo boardSelectOne(int bidx) {
		BoardVo bv = null;
		ResultSet rs = null;
		String sql = "select * from MG_BOARD where bidx=?";
		
		try {
			pstmt = conn.prepareStatement(sql);	//쿼리화 시킴
			pstmt.setInt(1, bidx);	//첫번째 물음표에 bidx값을 담아라
			rs= pstmt.executeQuery();
			
			if (rs.next()) { //다음값이 존재하면 true, 커서는 다음 행으로 이동
				bv=new BoardVo();
			
				bv.setBidx(rs.getInt("bidx")); //rs에 담겨져있는 데이터를 bv에 옮겨담는다
				bv.setOriginbidx(rs.getInt("Originbidx"));
				bv.setDepth(rs.getInt("Depth"));
				bv.setLevel_(rs.getInt("Level_"));
				
				
				bv.setSubject(rs.getString("subject"));
				bv.setContent(rs.getString("content"));
				bv.setWriter(rs.getString("writer"));
				bv.setWriteday(rs.getString("writeday"));
				bv.setFilename(rs.getString("filename"));
				bv.setFilesize(rs.getInt("filesize"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return bv;
	}
	
	//수정
	public int updateBoard (String subject, String content, String writer, String ip, int midx,int bidx_) {
		String sql = "update MG_BOARD set subject=?, content=?, writer=?, ip=?, midx=? where bidx=?";
		int value=0;
		
		try {
			pstmt = conn.prepareStatement(sql); //쿼리화
			pstmt.setString(1, subject);
			pstmt.setString(2, content);
			pstmt.setString(3, writer);
			pstmt.setString(4, ip);
			pstmt.setInt(5,midx);
			pstmt.setInt(6, bidx_);
			value = pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return value;
	}
	

	//전체 페이지 개수 뽑아오기
	public int boardTotal(SearchCriteria scri) {
		int cnt = 0;
		ResultSet rs = null;
		String str = "";
		
		if (scri.getSearchType().equals("subject")) {
			 str = "and subject like ?";
		}else {
			str = "and writer like ?";
		}
		
		String sql="select count(*) as cnt from MG_BOARD where delyn='N' "+str+" ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+scri.getKeyword()+"%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	public int deleteBoard (int bidx) {
		String sql = "update MG_BOARD set delyn='Y', writeday=sysdate where bidx=?";
		int value=0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bidx);
			value = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close(); //자원 반납 
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return value;
	}
	
	
}





