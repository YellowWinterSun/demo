package com.dayi.demo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OfficialEvaluationItemExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OfficialEvaluationItemExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNull() {
            addCriterion("updatetime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("updatetime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(Date value) {
            addCriterion("updatetime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Date value) {
            addCriterion("updatetime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Date value) {
            addCriterion("updatetime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updatetime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Date value) {
            addCriterion("updatetime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("updatetime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Date> values) {
            addCriterion("updatetime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Date> values) {
            addCriterion("updatetime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Date value1, Date value2) {
            addCriterion("updatetime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("updatetime not between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andOfficialIdIsNull() {
            addCriterion("official_id is null");
            return (Criteria) this;
        }

        public Criteria andOfficialIdIsNotNull() {
            addCriterion("official_id is not null");
            return (Criteria) this;
        }

        public Criteria andOfficialIdEqualTo(String value) {
            addCriterion("official_id =", value, "officialId");
            return (Criteria) this;
        }

        public Criteria andOfficialIdNotEqualTo(String value) {
            addCriterion("official_id <>", value, "officialId");
            return (Criteria) this;
        }

        public Criteria andOfficialIdGreaterThan(String value) {
            addCriterion("official_id >", value, "officialId");
            return (Criteria) this;
        }

        public Criteria andOfficialIdGreaterThanOrEqualTo(String value) {
            addCriterion("official_id >=", value, "officialId");
            return (Criteria) this;
        }

        public Criteria andOfficialIdLessThan(String value) {
            addCriterion("official_id <", value, "officialId");
            return (Criteria) this;
        }

        public Criteria andOfficialIdLessThanOrEqualTo(String value) {
            addCriterion("official_id <=", value, "officialId");
            return (Criteria) this;
        }

        public Criteria andOfficialIdLike(String value) {
            addCriterion("official_id like", value, "officialId");
            return (Criteria) this;
        }

        public Criteria andOfficialIdNotLike(String value) {
            addCriterion("official_id not like", value, "officialId");
            return (Criteria) this;
        }

        public Criteria andOfficialIdIn(List<String> values) {
            addCriterion("official_id in", values, "officialId");
            return (Criteria) this;
        }

        public Criteria andOfficialIdNotIn(List<String> values) {
            addCriterion("official_id not in", values, "officialId");
            return (Criteria) this;
        }

        public Criteria andOfficialIdBetween(String value1, String value2) {
            addCriterion("official_id between", value1, value2, "officialId");
            return (Criteria) this;
        }

        public Criteria andOfficialIdNotBetween(String value1, String value2) {
            addCriterion("official_id not between", value1, value2, "officialId");
            return (Criteria) this;
        }

        public Criteria andDimensionalityNameIsNull() {
            addCriterion("dimensionality_name is null");
            return (Criteria) this;
        }

        public Criteria andDimensionalityNameIsNotNull() {
            addCriterion("dimensionality_name is not null");
            return (Criteria) this;
        }

        public Criteria andDimensionalityNameEqualTo(String value) {
            addCriterion("dimensionality_name =", value, "dimensionalityName");
            return (Criteria) this;
        }

        public Criteria andDimensionalityNameNotEqualTo(String value) {
            addCriterion("dimensionality_name <>", value, "dimensionalityName");
            return (Criteria) this;
        }

        public Criteria andDimensionalityNameGreaterThan(String value) {
            addCriterion("dimensionality_name >", value, "dimensionalityName");
            return (Criteria) this;
        }

        public Criteria andDimensionalityNameGreaterThanOrEqualTo(String value) {
            addCriterion("dimensionality_name >=", value, "dimensionalityName");
            return (Criteria) this;
        }

        public Criteria andDimensionalityNameLessThan(String value) {
            addCriterion("dimensionality_name <", value, "dimensionalityName");
            return (Criteria) this;
        }

        public Criteria andDimensionalityNameLessThanOrEqualTo(String value) {
            addCriterion("dimensionality_name <=", value, "dimensionalityName");
            return (Criteria) this;
        }

        public Criteria andDimensionalityNameLike(String value) {
            addCriterion("dimensionality_name like", value, "dimensionalityName");
            return (Criteria) this;
        }

        public Criteria andDimensionalityNameNotLike(String value) {
            addCriterion("dimensionality_name not like", value, "dimensionalityName");
            return (Criteria) this;
        }

        public Criteria andDimensionalityNameIn(List<String> values) {
            addCriterion("dimensionality_name in", values, "dimensionalityName");
            return (Criteria) this;
        }

        public Criteria andDimensionalityNameNotIn(List<String> values) {
            addCriterion("dimensionality_name not in", values, "dimensionalityName");
            return (Criteria) this;
        }

        public Criteria andDimensionalityNameBetween(String value1, String value2) {
            addCriterion("dimensionality_name between", value1, value2, "dimensionalityName");
            return (Criteria) this;
        }

        public Criteria andDimensionalityNameNotBetween(String value1, String value2) {
            addCriterion("dimensionality_name not between", value1, value2, "dimensionalityName");
            return (Criteria) this;
        }

        public Criteria andDimensionalityLevelIsNull() {
            addCriterion("dimensionality_level is null");
            return (Criteria) this;
        }

        public Criteria andDimensionalityLevelIsNotNull() {
            addCriterion("dimensionality_level is not null");
            return (Criteria) this;
        }

        public Criteria andDimensionalityLevelEqualTo(Byte value) {
            addCriterion("dimensionality_level =", value, "dimensionalityLevel");
            return (Criteria) this;
        }

        public Criteria andDimensionalityLevelNotEqualTo(Byte value) {
            addCriterion("dimensionality_level <>", value, "dimensionalityLevel");
            return (Criteria) this;
        }

        public Criteria andDimensionalityLevelGreaterThan(Byte value) {
            addCriterion("dimensionality_level >", value, "dimensionalityLevel");
            return (Criteria) this;
        }

        public Criteria andDimensionalityLevelGreaterThanOrEqualTo(Byte value) {
            addCriterion("dimensionality_level >=", value, "dimensionalityLevel");
            return (Criteria) this;
        }

        public Criteria andDimensionalityLevelLessThan(Byte value) {
            addCriterion("dimensionality_level <", value, "dimensionalityLevel");
            return (Criteria) this;
        }

        public Criteria andDimensionalityLevelLessThanOrEqualTo(Byte value) {
            addCriterion("dimensionality_level <=", value, "dimensionalityLevel");
            return (Criteria) this;
        }

        public Criteria andDimensionalityLevelIn(List<Byte> values) {
            addCriterion("dimensionality_level in", values, "dimensionalityLevel");
            return (Criteria) this;
        }

        public Criteria andDimensionalityLevelNotIn(List<Byte> values) {
            addCriterion("dimensionality_level not in", values, "dimensionalityLevel");
            return (Criteria) this;
        }

        public Criteria andDimensionalityLevelBetween(Byte value1, Byte value2) {
            addCriterion("dimensionality_level between", value1, value2, "dimensionalityLevel");
            return (Criteria) this;
        }

        public Criteria andDimensionalityLevelNotBetween(Byte value1, Byte value2) {
            addCriterion("dimensionality_level not between", value1, value2, "dimensionalityLevel");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andWeightIsNull() {
            addCriterion("weight is null");
            return (Criteria) this;
        }

        public Criteria andWeightIsNotNull() {
            addCriterion("weight is not null");
            return (Criteria) this;
        }

        public Criteria andWeightEqualTo(Byte value) {
            addCriterion("weight =", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotEqualTo(Byte value) {
            addCriterion("weight <>", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThan(Byte value) {
            addCriterion("weight >", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThanOrEqualTo(Byte value) {
            addCriterion("weight >=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThan(Byte value) {
            addCriterion("weight <", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThanOrEqualTo(Byte value) {
            addCriterion("weight <=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightIn(List<Byte> values) {
            addCriterion("weight in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotIn(List<Byte> values) {
            addCriterion("weight not in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightBetween(Byte value1, Byte value2) {
            addCriterion("weight between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotBetween(Byte value1, Byte value2) {
            addCriterion("weight not between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andSelfContentIsNull() {
            addCriterion("self_content is null");
            return (Criteria) this;
        }

        public Criteria andSelfContentIsNotNull() {
            addCriterion("self_content is not null");
            return (Criteria) this;
        }

        public Criteria andSelfContentEqualTo(String value) {
            addCriterion("self_content =", value, "selfContent");
            return (Criteria) this;
        }

        public Criteria andSelfContentNotEqualTo(String value) {
            addCriterion("self_content <>", value, "selfContent");
            return (Criteria) this;
        }

        public Criteria andSelfContentGreaterThan(String value) {
            addCriterion("self_content >", value, "selfContent");
            return (Criteria) this;
        }

        public Criteria andSelfContentGreaterThanOrEqualTo(String value) {
            addCriterion("self_content >=", value, "selfContent");
            return (Criteria) this;
        }

        public Criteria andSelfContentLessThan(String value) {
            addCriterion("self_content <", value, "selfContent");
            return (Criteria) this;
        }

        public Criteria andSelfContentLessThanOrEqualTo(String value) {
            addCriterion("self_content <=", value, "selfContent");
            return (Criteria) this;
        }

        public Criteria andSelfContentLike(String value) {
            addCriterion("self_content like", value, "selfContent");
            return (Criteria) this;
        }

        public Criteria andSelfContentNotLike(String value) {
            addCriterion("self_content not like", value, "selfContent");
            return (Criteria) this;
        }

        public Criteria andSelfContentIn(List<String> values) {
            addCriterion("self_content in", values, "selfContent");
            return (Criteria) this;
        }

        public Criteria andSelfContentNotIn(List<String> values) {
            addCriterion("self_content not in", values, "selfContent");
            return (Criteria) this;
        }

        public Criteria andSelfContentBetween(String value1, String value2) {
            addCriterion("self_content between", value1, value2, "selfContent");
            return (Criteria) this;
        }

        public Criteria andSelfContentNotBetween(String value1, String value2) {
            addCriterion("self_content not between", value1, value2, "selfContent");
            return (Criteria) this;
        }

        public Criteria andSelfGradeIsNull() {
            addCriterion("self_grade is null");
            return (Criteria) this;
        }

        public Criteria andSelfGradeIsNotNull() {
            addCriterion("self_grade is not null");
            return (Criteria) this;
        }

        public Criteria andSelfGradeEqualTo(Byte value) {
            addCriterion("self_grade =", value, "selfGrade");
            return (Criteria) this;
        }

        public Criteria andSelfGradeNotEqualTo(Byte value) {
            addCriterion("self_grade <>", value, "selfGrade");
            return (Criteria) this;
        }

        public Criteria andSelfGradeGreaterThan(Byte value) {
            addCriterion("self_grade >", value, "selfGrade");
            return (Criteria) this;
        }

        public Criteria andSelfGradeGreaterThanOrEqualTo(Byte value) {
            addCriterion("self_grade >=", value, "selfGrade");
            return (Criteria) this;
        }

        public Criteria andSelfGradeLessThan(Byte value) {
            addCriterion("self_grade <", value, "selfGrade");
            return (Criteria) this;
        }

        public Criteria andSelfGradeLessThanOrEqualTo(Byte value) {
            addCriterion("self_grade <=", value, "selfGrade");
            return (Criteria) this;
        }

        public Criteria andSelfGradeIn(List<Byte> values) {
            addCriterion("self_grade in", values, "selfGrade");
            return (Criteria) this;
        }

        public Criteria andSelfGradeNotIn(List<Byte> values) {
            addCriterion("self_grade not in", values, "selfGrade");
            return (Criteria) this;
        }

        public Criteria andSelfGradeBetween(Byte value1, Byte value2) {
            addCriterion("self_grade between", value1, value2, "selfGrade");
            return (Criteria) this;
        }

        public Criteria andSelfGradeNotBetween(Byte value1, Byte value2) {
            addCriterion("self_grade not between", value1, value2, "selfGrade");
            return (Criteria) this;
        }

        public Criteria andBossGradeIsNull() {
            addCriterion("boss_grade is null");
            return (Criteria) this;
        }

        public Criteria andBossGradeIsNotNull() {
            addCriterion("boss_grade is not null");
            return (Criteria) this;
        }

        public Criteria andBossGradeEqualTo(Byte value) {
            addCriterion("boss_grade =", value, "bossGrade");
            return (Criteria) this;
        }

        public Criteria andBossGradeNotEqualTo(Byte value) {
            addCriterion("boss_grade <>", value, "bossGrade");
            return (Criteria) this;
        }

        public Criteria andBossGradeGreaterThan(Byte value) {
            addCriterion("boss_grade >", value, "bossGrade");
            return (Criteria) this;
        }

        public Criteria andBossGradeGreaterThanOrEqualTo(Byte value) {
            addCriterion("boss_grade >=", value, "bossGrade");
            return (Criteria) this;
        }

        public Criteria andBossGradeLessThan(Byte value) {
            addCriterion("boss_grade <", value, "bossGrade");
            return (Criteria) this;
        }

        public Criteria andBossGradeLessThanOrEqualTo(Byte value) {
            addCriterion("boss_grade <=", value, "bossGrade");
            return (Criteria) this;
        }

        public Criteria andBossGradeIn(List<Byte> values) {
            addCriterion("boss_grade in", values, "bossGrade");
            return (Criteria) this;
        }

        public Criteria andBossGradeNotIn(List<Byte> values) {
            addCriterion("boss_grade not in", values, "bossGrade");
            return (Criteria) this;
        }

        public Criteria andBossGradeBetween(Byte value1, Byte value2) {
            addCriterion("boss_grade between", value1, value2, "bossGrade");
            return (Criteria) this;
        }

        public Criteria andBossGradeNotBetween(Byte value1, Byte value2) {
            addCriterion("boss_grade not between", value1, value2, "bossGrade");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}