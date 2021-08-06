package com.impelitsolutions.service.diary.repository;

import com.impelitsolutions.service.common.utils.Utils;
import com.impelitsolutions.service.diary.payload.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class NoteDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(NoteDao.class);

    @Autowired
    private IBaseRepository baseRepository;

    public NoteCategoryResponse getNoteCategory(NoteCategory criteria, Integer userId){
        List<NoteCategory> list = new ArrayList<>();
        NoteCategoryResponse response = new NoteCategoryResponse();
        Map<String, Object> param = new HashMap<>();
        String sql = "SELECT * FROM note_category WHERE 1=1 ";
        String sqlCount = "SELECT count(id) FROM note_category WHERE 1=1 ";
        if (criteria != null && Utils.isOk(criteria.getId())) {
            sql += " AND id =:categoryId " ;
            sqlCount += " AND id =:categoryId ";
            param.put("categoryId", criteria.getId());
        }

        if (criteria != null && Utils.isOk(criteria.getName())) {
            sql += " AND UPPER(name) like :name";
            sqlCount += " AND UPPER(name) like :name";
            param.put("name", criteria.getName());
        }

        if(Utils.isOk(userId)){
            sql += " AND created_by =:user_id ";
            sqlCount += " AND created_by =:user_id ";
            param.put("user_id", userId);
        }
        sql += " order by name ";
        List<com.impelitsolutions.service.diary.model.NoteCategory> noteInfos = baseRepository.findByNativeQuery(sql, com.impelitsolutions.service.diary.model.NoteCategory.class, param);
        if (noteInfos != null && noteInfos.size() > 0) {
            noteInfos.forEach(n -> list.add(new NoteCategory(n)));
            Integer count = baseRepository.findCountByNativeQuery(sqlCount, param);
            response.setTotal(count);
        }
        response.setCategoryList(list);
        return response;

    }

    public NoteInfoResponse getNoteInfos(NoteSearchCriteria  criteria, Integer userId){
        List<NoteInfo> list = new ArrayList<>();
        NoteInfoResponse response = new NoteInfoResponse();
        Map<String, Object> param = new HashMap<>();
        String sql = "SELECT * FROM note_info WHERE 1=1 ";
        String sqlCount = "SELECT count(id) FROM note_info WHERE 1=1 ";
        if (criteria != null && Utils.isOk(criteria.getNoteId())) {
            sql += " AND id =:noteId " ;
            sqlCount += " AND id =:noteId ";
            param.put("noteId", criteria.getNoteId());
        }
        if (criteria != null && Utils.isOk(criteria.getCategory())) {
            sql += " AND category_id =:category_id ";
            sqlCount += " AND category_id =:category_id ";
            param.put("category_id", criteria.getCategory());
        }
        if (criteria != null && Utils.isOk(criteria.getTitle())) {
            sql += " AND UPPER(title) like :category";
            sqlCount += " AND UPPER(title) like :category";
            param.put("category", criteria.getTitle());
        }

        if(Utils.isOk(userId)){
            sql += " AND created_by =:user_id ";
            sqlCount += " AND created_by =:user_id ";
            param.put("user_id", userId);
        }
        sql += " order by title ";
        List<com.impelitsolutions.service.diary.model.NoteInfo> noteInfos = baseRepository.findByNativeQuery(sql, com.impelitsolutions.service.diary.model.NoteInfo.class, param);
        if (noteInfos != null && noteInfos.size() > 0) {
            noteInfos.forEach(n -> list.add(new NoteInfo(n)));
            Integer count = baseRepository.findCountByNativeQuery(sqlCount, param);
            response.setTotal(count);
        }
        response.setNoteInfoList(list);
        return response;

    }
}
