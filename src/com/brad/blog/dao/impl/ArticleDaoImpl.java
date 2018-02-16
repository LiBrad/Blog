package com.brad.blog.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.brad.blog.bean.Article;
import com.brad.blog.bean.Category;
import com.brad.blog.dao.ArticleDao;
import com.brad.blog.db.DBPool;

/**
 * @author Brad
 * @version 0.1
 */
public class ArticleDaoImpl implements ArticleDao {

	@Override
	public Article getById(int id) {
		Article art = null;
		ResultSet rs = null;
		DBPool db = new DBPool();
		try {
			String sql = "select a.*,u.name from article as a,admin as u where a.id = ? and a.user_id=u.id";
			rs = db.doQueryRs(sql, new Object[] { id });

			while (rs.next()) {
				art = new Article(rs.getInt("id"), rs.getString("title"), rs.getInt("user_id"),
						rs.getInt("category_id"), rs.getString("content"), rs.getString("summary"),
						rs.getTimestamp("publish_time"), rs.getInt("is_top"), rs.getInt("is_delete"),
						rs.getInt("count"));
				art.setName(rs.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}

		return art;
	}

	@Override
	public int inserArticle(String title, int userId, int cgId, String content, String summary) {
		int res = -1;
		DBPool db = new DBPool();
		try {
			String sql = "insert into article(title,user_id,category_id,content,summary) values(?,?,?,?,?)";
			res = db.doUpdate(sql, new Object[] { title, userId, cgId, content, summary });
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return res;
	}

	@Override
	public int updateArticle(int arId, String title, int userId, int cgId, String content, String summary) {
		int res = -1;
		DBPool db = new DBPool();
		try {
			String sql = "update article "
					+ "set title=?,user_id=?,category_id=?,content=?,summary=?,publish_time=NOW(),is_top=0,is_delete=0 where id = ?";
			res = db.doUpdate(sql, new Object[] { title, userId, cgId, content, summary, arId });
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return res;
	}

	@Override
	public int deleteArtical(int arId,int adminId) {
		int res = -1;
		DBPool db = new DBPool();
		try {
			String sql = "update article set is_delete = 1 where id = ? and user_id = ? ";
			res = db.doUpdate(sql, new Object[] { arId ,adminId});
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return res;
	}

	@Override
	public List<Article> getAllArtical() {
		List<Article> artList = null;
		ResultSet rs = null;
		DBPool db = new DBPool();
		try {
			String sql = "select * from article where is_delete=0 order by publish_time DESC";
			artList = new ArrayList<Article>();

			rs = db.doQueryRs(sql, new Object[] {});

			while (rs.next()) {
				Article art = new Article(rs.getInt("id"), rs.getString("title"), rs.getInt("user_id"),
						rs.getInt("category_id"), rs.getString("content"), rs.getString("summary"),
						rs.getTimestamp("publish_time"), rs.getInt("is_top"), rs.getInt("is_delete"),
						rs.getInt("count"));
				artList.add(art);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}

		return artList;
	}

	@Override
	public List<Article> search(String q,int curPage,int size) {
		List<Article> artList = null;
		ResultSet rs = null;
		DBPool db = new DBPool();
		try {
			String sql = "select * from article where title like ? or content like ? or summary like ? order by publish_time DESC limit ?,?";
			q = '%' + q + '%';
			rs = db.doQueryRs(sql, new Object[] { q, q, q ,curPage,size});
			artList = new ArrayList<Article>();
			while (rs.next()) {
				Article art = new Article(rs.getInt("id"), rs.getString("title"), rs.getInt("user_id"),
						rs.getInt("category_id"), rs.getString("content"), rs.getString("summary"),
						rs.getTimestamp("publish_time"), rs.getInt("is_top"), rs.getInt("is_delete"),
						rs.getInt("count"));
				artList.add(art);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}

		return artList;
	}

	@Override
	public List<Article> getArticleByPage(int curPage, int size) {
		List<Article> artList = null;
		ResultSet rs = null;
		int start = (curPage - 1) * size;
		DBPool db = new DBPool();
		try {
			String sql = "select a.*,u.name from article as a ,admin as u where u.id=a.user_id and a.is_delete=0 order by publish_time DESC limit ?,?";
			artList = new ArrayList<Article>();
			rs = db.doQueryRs(sql, new Object[] { start, size });

			while (rs.next()) {
				Article art = new Article();
				art.setId(rs.getInt("id"));
				art.setTitle(rs.getString("title"));
				art.setUserId(rs.getInt("user_id"));
				art.setCategoryId(rs.getInt("category_id"));
				art.setContent(rs.getString("content"));
				art.setSummary(rs.getString("summary"));
				art.setPublishTime(rs.getTimestamp("publish_time"));
				art.setIsTop(rs.getInt("is_top"));
				art.setIsDelete(rs.getInt("is_delete"));
				art.setCount(rs.getInt("count"));
				try {
					art.setName(rs.getString("name"));
				} catch (Exception e) {
					art.setName("");
				}
				artList.add(art);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}

		return artList;
	}

	@Override
	public List<Article> topTenArticle() {
		List<Article> artList = null;
		ResultSet rs = null;
		DBPool db = new DBPool();
		try {
			artList = new ArrayList<Article>();
			String sql = "select * from article order by count desc limit 10";
			rs = db.doQueryRs(sql, new Object[] {});
			while (rs.next()) {
				Article art = new Article(rs.getInt("id"), rs.getString("title"), rs.getInt("user_id"),
						rs.getInt("category_id"), rs.getString("content"), rs.getString("summary"),
						rs.getTimestamp("publish_time"), rs.getInt("is_top"), rs.getInt("is_delete"),
						rs.getInt("count"));
				artList.add(art);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}

		return artList;
	}

	@Override
	public int updateCount(int artId) {
		int res = -1;
		DBPool db = new DBPool();
		try {
			String sql = "update article set count = count + 1 where id = ?";
			res = db.doUpdate(sql, new Object[] { artId });
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}
		return res;
	}

	@Override
	public List<Article> getArticleByLabelPage(int cgId, int curPage, int size) {
		List<Article> artList = null;
		ResultSet rs = null;
		int start = (curPage - 1) * size;
		DBPool db = new DBPool();
		try {
			String sql = "select a.*,u.name from article as a ,admin as u where category_id = ? and u.id=a.user_id and a.is_delete=0 order by publish_time DESC limit ?,?";
			artList = new ArrayList<Article>();
			rs = db.doQueryRs(sql, new Object[] { cgId, start, size });

			while (rs.next()) {
				Article art = new Article();
				art.setId(rs.getInt("id"));
				art.setTitle(rs.getString("title"));
				art.setUserId(rs.getInt("user_id"));
				art.setCategoryId(rs.getInt("category_id"));
				art.setContent(rs.getString("content"));
				art.setSummary(rs.getString("summary"));
				art.setPublishTime(rs.getTimestamp("publish_time"));
				art.setIsTop(rs.getInt("is_top"));
				art.setIsDelete(rs.getInt("is_delete"));
				art.setCount(rs.getInt("count"));
				try {
					art.setName(rs.getString("name"));
				} catch (Exception e) {
					art.setName("");
				}
				artList.add(art);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}

		return artList;
	}

	@Override
	public List<Article> getArticleByAdmin(int admin_id, int curPage, int size) {
		ResultSet rs = null;
		List<Article> artList = null;
		int start = (curPage - 1) * size; // limit从0开始
		DBPool dbc = new DBPool();
		try {
			String sql = "SELECT article.*,c.category_name,a.name FROM article  "
					+ "INNER JOIN category AS c ON article.category_id = c.id "
					+ "INNER JOIN admin AS a ON article.user_id = a.id AND c.user_id=a.id "
					+ "WHERE article.is_delete = 0 and article.user_id = ? ORDER BY publish_time DESC limit ?,?";

			artList = new ArrayList<Article>();
			rs = dbc.doQueryRs(sql, new Object[] { admin_id, start, size });
			while (rs.next()) {
				Article art = new Article();

				art.setId(rs.getInt("id"));
				art.setTitle(rs.getString("title"));
				art.setUserId(rs.getInt("user_id"));
				art.setCategoryId(rs.getInt("category_id"));
				art.setContent(rs.getString("content"));
				art.setSummary(rs.getString("summary"));
				art.setPublishTime(rs.getTimestamp("publish_time"));
				art.setIsTop(rs.getInt("is_top"));
				art.setIsDelete(rs.getInt("is_delete"));
				art.setCount(rs.getInt("count"));
				try {

					art.setCname(rs.getString("category_name"));
					art.setName(rs.getString("name"));
				} catch (Exception e) {
					art.setCname("");
					art.setName("");
				}

				artList.add(art);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			dbc.close();
		}

		return artList;
	}

	@Override
	public List<Article> getAdminId(int user_id) {
		List<Article> artList = null;
		ResultSet rs = null;
		DBPool db = new DBPool();
		try {
			String sql = "select * from article where user_id = ? and  is_delete=0 order by publish_time DESC";
			artList = new ArrayList<Article>();

			rs = db.doQueryRs(sql, new Object[] {user_id});

			while (rs.next()) {
				Article art = new Article(rs.getInt("id"), rs.getString("title"), rs.getInt("user_id"),
						rs.getInt("category_id"), rs.getString("content"), rs.getString("summary"),
						rs.getTimestamp("publish_time"), rs.getInt("is_top"), rs.getInt("is_delete"),
						rs.getInt("count"));
				artList.add(art);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}

		return artList;
	}

	@Override
	public int searchCount(String q) {
		int count = -1;
		ResultSet rs = null;
		DBPool db = new DBPool();
		try {
			String sql = "select count(*) from article where title like ? or content like ? or summary like ? order by publish_time DESC ";
			q = '%' + q + '%';
			rs = db.doQueryRs(sql, new Object[] { q, q, q });
			while (rs.next()) {
				count=rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close();
		}

		return count;
	}

}
