package com.app.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.app.dao.IDocumentDao;
import com.app.model.Document;
@Repository
public class DocumentDaoImpl implements IDocumentDao {
	@Autowired
	private HibernateTemplate ht;
	
	@Override
	public void saveDocument(Document doc) {
		ht.save(doc);
	}
	
	@Override
	public List<Object[]> getFileIdAndNames() {
		String hql="select fileId,fileName from "+Document.class.getName();
		List<Object[]> list=ht.find(hql);
		return list;
	}
	
	@Override
	public Document getDocById(int docId) {
		return ht.get(Document.class, docId);
	}
}
