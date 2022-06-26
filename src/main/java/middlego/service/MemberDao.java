package middlego.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.websocket.Session;

import middlego.dbconn.Dbconn;
import middlego.domain.MemberVo;



public class MemberDao {
	
	private Connection conn;
	private PreparedStatement pstmt;
	
	
	public MemberDao() {
		Dbconn db = new Dbconn();
		this.conn = db.getConnection();
	}
	
public int insertMember(String memberId, String memberPwd, String memberName, String memberAddr, String memberJumin, String memberPhone, String memberMail, String ip){
	int value=0;
	
	String sql = "insert into MG_MEMBER(MIDX,MEMBERID,MEMBERPWD,MEMBERNAME,MEMBERADDER,MEMBERJUMIN,"
			+"MEMBERPHONE,MEMBERMAIL,MEMBERIP)"
			+"values(MIDX_MG_SEQ.nextval,?,?,?,?,?,?,?,?)";
	
	//Statement  -> prepareStatment
	//PreparedStatement : 변수를 직접 넣지 않고 메소드를 통해 집어넣게 처리하는 방식
	try {
		//conn객체 안에 있는 prepareStatement를 사용
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memberId); //1 <- 첫번째 물음표
		pstmt.setString(2, memberPwd);
		pstmt.setString(3, memberName);
		pstmt.setString(4, memberAddr);
		pstmt.setString(5, memberJumin);
		pstmt.setString(6, memberPhone);
		pstmt.setString(7, memberMail);
		pstmt.setString(8,ip);
	value = pstmt.executeUpdate();
		
	//연결 객체를 통해서 Statement 구문 객체를 생성해서 stmt에 담는다
	//Statement stmt = conn.createStatement();
	//구문을 실행하고 리턴값으로 실행되었으면 1 아니면 0 을 value 변수에 담는다
	//value = pstmt.executeUpdate(sql);
	
	}catch(Exception e){
		e.printStackTrace();
	}finally {
		try {
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return value;	
}

	int value=0;

	String sql = "insert into MG_MEMBER(MIDX,MEMBERID,MEMBERPWD,MEMBERNAME,MEMBERADDERMEMBERJUMIN,MEMBERPHONE,MEMBERMAIL,MEMBERIP)"
				+"values(MIDX_MG_SEQ.nextval,?,?,?,?,?,?,?,?,?,?)";

//insert 
	public ArrayList<MemberVo> memberSelectAll() {
	ArrayList<MemberVo> alist = new ArrayList<MemberVo>();
	ResultSet rs = null;
	
	//쿼리 실행, 쿼리구문을 문자열로 만들어놓는다
	String sql= "select * from MG_MEMBER where delyn = 'N' order by midx desc";
	
	//연결 객체에 있는 pripareStatement 메소드를 실행해서 sql 문자열을 담아 구문객체를 만든다
	try{
	pstmt = conn.prepareStatement(sql);
	rs = pstmt.executeQuery();
	
	while(rs.next()){
		//반복할때마다 객체 생성한다
		MemberVo mv = new MemberVo();
		
		//rs에 담아놓은 컬럼값들을 mv에 옮겨담는다.
		mv.setMidx(rs.getInt("midx"));
		mv.setMembername(rs.getString("membername"));
		mv.setMemberphone(rs.getString("memberphone"));
		mv.setWriteday(rs.getString("writeday"));
		//alist에 값을 담은 객체를 추가한다
		alist.add(mv);	
	}
	
	}catch(Exception e){
		e.printStackTrace();
	}finally {
		try {
			conn.close(); //연결객체 끊어서 메모리 관리
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	return alist;
}
	public MemberVo memberLogin(String memberId, String memberPwd) {
		MemberVo mv = null;
		ResultSet rs = null;
		String sql = "select * from MG_MEMBER WHERE DELYN='N' AND memberId=? AND memberPwd=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPwd);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mv = new MemberVo();
				mv.setMidx(rs.getInt("midx"));
				mv.setMemberid(rs.getString("memberid"));
				mv.setMembername(rs.getString("Membername"));
				
			}
		
		}catch (SQLException e) {
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
		return mv; 
		
		
	}
	
	public String memberIdf(String memberName, String memberPhone) {
		System.out.println(memberName + memberPhone);
		ResultSet rs = null;
		String id = null;
		try {
			String sql = "select MEMBERID from MG_MEMBER where MEMBERNAME=? and MEMBERPHONE=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberName);
			pstmt.setString(2, memberPhone);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				id = rs.getString("memberid");
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public String memberPwdf(String memberId, String memberPhone) {
		System.out.println(memberId + memberPhone);
		ResultSet rs = null;
		String id = null;
		try {
			String sql = "select MEMBERPWD from MG_MEMBER where MEMBERID=? and MEMBERPHONE=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPhone);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				id = rs.getString("memberpwd");
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public MemberVo selectMember(String memberId) {
		ResultSet rs = null;
		String sql = "select * from MG_MEMBER WHERE MEMBERID=? ";

		MemberVo mv = new MemberVo();
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rs=pstmt.executeQuery();

			if(rs.next()){
				mv.setMidx(rs.getInt("midx"));
				mv.setMemberid(rs.getString("memberid"));
				mv.setMemberpwd(rs.getString("memberpwd"));
				mv.setMembername(rs.getString("membername"));
				mv.setMemberjumin(rs.getString("memberjumin"));
				mv.setMemberadder(rs.getString("memberadder"));
				mv.setMemberphone(rs.getString("memberphone"));
				mv.setMembermail(rs.getString("membermail"));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				conn.close(); //연결객체 끊어서 메모리 관리
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
		return mv;
	}
	
	
	public MemberVo memberSelectOne(int midx) {
		MemberVo mv = null;
		ResultSet rs = null;
		String sql = "select * from MG_MEMBER where midx=?";
		
		try {
			pstmt = conn.prepareStatement(sql);	//쿼리화 시킴
			pstmt.setInt(1, midx);	//첫번째 물음표에 bidx값을 담아라
			rs= pstmt.executeQuery();
			
			if (rs.next()) { //다음값이 존재하면 true, 커서는 다음 행으로 이동
				mv=new MemberVo();
				mv.setMidx(rs.getInt("midx"));
				mv.setMemberid(rs.getString("memberid"));
				mv.setMemberpwd(rs.getString("memberpwd"));
				mv.setMembername(rs.getString("membername"));
				mv.setMemberjumin(rs.getString("memberjumin"));
				mv.setMemberadder(rs.getString("memberadder"));
				mv.setMemberphone(rs.getString("memberphone"));
				mv.setMembermail(rs.getString("membermail"));
			
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
		
		
		return mv;
	}

	
}
