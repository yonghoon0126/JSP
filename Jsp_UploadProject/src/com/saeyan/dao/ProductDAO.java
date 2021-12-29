package com.saeyan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;

import com.saeyan.dto.ProductVO;

public class ProductDAO {

	private ProductDAO() {

	}

	private static ProductDAO instance = new ProductDAO();

	public static ProductDAO getInstance() {
		return instance;
	}

	// c Read u d
	public List<ProductVO> selectAllProducts() {
		// 최근 등록한 상품 먼저 출력하기
		String sql = "select * from product order by code desc";
		List<ProductVO> list = new ArrayList<ProductVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) { // 이동은 행(로우) 단위로
				ProductVO pVo = new ProductVO();
				pVo.setCode(rs.getInt("code"));
				pVo.setName(rs.getString("name"));
				pVo.setPrice(rs.getInt("price"));
				pVo.setPictureUrl(rs.getString("pictureUrl"));
				pVo.setDescription(rs.getString("description"));
				System.out.println("..." + rs.getInt("code"));
				list.add(pVo);
			} // while문 끝

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		System.out.println("list 보냄..." + list.toString());
		return list;

	} // selectAllProducts(){

	
	// Create r u d
	public void insertProduct(ProductVO pVo) {
		String sql = "insert into product values(product_seq.nextval, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pVo.getName());
			pstmt.setInt(2, pVo.getPrice());
			pstmt.setString(3, pVo.getPictureUrl());
			pstmt.setString(4, pVo.getDescription());
			pstmt.executeUpdate(); // 실행

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

	}
	
	
	// c Read u d
	public ProductVO selectProductByCode(String code) {
		String sql = "select * from product where code=?";
		ProductVO pVo = null;
		
		try {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, code);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					pVo = new ProductVO();
					pVo.setCode(rs.getInt("code"));
					pVo.setName(rs.getString("name"));
					pVo.setPrice(rs.getInt("price"));
					pVo.setPictureUrl(rs.getString("pictureUrl"));
					pVo.setDescription(rs.getString("description"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				DBManager.close(conn, pstmt, rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pVo;
	}

	
	// c r Update d
	public void updateProduct(ProductVO pVo) {
		String sql = "update product set name=?, price=?, pictureurl=?, description=? where code=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  pVo.getName());
			pstmt.setInt(2,  pVo.getPrice());
			pstmt.setString(3,  pVo.getPictureUrl());
			pstmt.setString(4,  pVo.getDescription());
			pstmt.setInt(5,  pVo.getCode());
			pstmt.executeUpdate();	// 쿼리문 실행한다.
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	
	// c r u Delete
	public void deleteProduct(String code) {
		String sql = "delete product where code=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			pstmt.executeUpdate();	// 쿼리문 실행
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
	}
	
} // ProductDAO{



