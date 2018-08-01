package com.dayi.demo.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OfficialPerformanceEvaluationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OfficialPerformanceEvaluationExample() {
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

        public Criteria andDepartmentNameIsNull() {
            addCriterion("department_name is null");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameIsNotNull() {
            addCriterion("department_name is not null");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameEqualTo(String value) {
            addCriterion("department_name =", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameNotEqualTo(String value) {
            addCriterion("department_name <>", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameGreaterThan(String value) {
            addCriterion("department_name >", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameGreaterThanOrEqualTo(String value) {
            addCriterion("department_name >=", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameLessThan(String value) {
            addCriterion("department_name <", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameLessThanOrEqualTo(String value) {
            addCriterion("department_name <=", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameLike(String value) {
            addCriterion("department_name like", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameNotLike(String value) {
            addCriterion("department_name not like", value, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameIn(List<String> values) {
            addCriterion("department_name in", values, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameNotIn(List<String> values) {
            addCriterion("department_name not in", values, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameBetween(String value1, String value2) {
            addCriterion("department_name between", value1, value2, "departmentName");
            return (Criteria) this;
        }

        public Criteria andDepartmentNameNotBetween(String value1, String value2) {
            addCriterion("department_name not between", value1, value2, "departmentName");
            return (Criteria) this;
        }

        public Criteria andJobNameIsNull() {
            addCriterion("job_name is null");
            return (Criteria) this;
        }

        public Criteria andJobNameIsNotNull() {
            addCriterion("job_name is not null");
            return (Criteria) this;
        }

        public Criteria andJobNameEqualTo(String value) {
            addCriterion("job_name =", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameNotEqualTo(String value) {
            addCriterion("job_name <>", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameGreaterThan(String value) {
            addCriterion("job_name >", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameGreaterThanOrEqualTo(String value) {
            addCriterion("job_name >=", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameLessThan(String value) {
            addCriterion("job_name <", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameLessThanOrEqualTo(String value) {
            addCriterion("job_name <=", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameLike(String value) {
            addCriterion("job_name like", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameNotLike(String value) {
            addCriterion("job_name not like", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameIn(List<String> values) {
            addCriterion("job_name in", values, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameNotIn(List<String> values) {
            addCriterion("job_name not in", values, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameBetween(String value1, String value2) {
            addCriterion("job_name between", value1, value2, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameNotBetween(String value1, String value2) {
            addCriterion("job_name not between", value1, value2, "jobName");
            return (Criteria) this;
        }

        public Criteria andYearIsNull() {
            addCriterion("year is null");
            return (Criteria) this;
        }

        public Criteria andYearIsNotNull() {
            addCriterion("year is not null");
            return (Criteria) this;
        }

        public Criteria andYearEqualTo(Short value) {
            addCriterion("year =", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotEqualTo(Short value) {
            addCriterion("year <>", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearGreaterThan(Short value) {
            addCriterion("year >", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearGreaterThanOrEqualTo(Short value) {
            addCriterion("year >=", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLessThan(Short value) {
            addCriterion("year <", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLessThanOrEqualTo(Short value) {
            addCriterion("year <=", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearIn(List<Short> values) {
            addCriterion("year in", values, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotIn(List<Short> values) {
            addCriterion("year not in", values, "year");
            return (Criteria) this;
        }

        public Criteria andYearBetween(Short value1, Short value2) {
            addCriterion("year between", value1, value2, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotBetween(Short value1, Short value2) {
            addCriterion("year not between", value1, value2, "year");
            return (Criteria) this;
        }

        public Criteria andMonthIsNull() {
            addCriterion("month is null");
            return (Criteria) this;
        }

        public Criteria andMonthIsNotNull() {
            addCriterion("month is not null");
            return (Criteria) this;
        }

        public Criteria andMonthEqualTo(Byte value) {
            addCriterion("month =", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthNotEqualTo(Byte value) {
            addCriterion("month <>", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthGreaterThan(Byte value) {
            addCriterion("month >", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthGreaterThanOrEqualTo(Byte value) {
            addCriterion("month >=", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthLessThan(Byte value) {
            addCriterion("month <", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthLessThanOrEqualTo(Byte value) {
            addCriterion("month <=", value, "month");
            return (Criteria) this;
        }

        public Criteria andMonthIn(List<Byte> values) {
            addCriterion("month in", values, "month");
            return (Criteria) this;
        }

        public Criteria andMonthNotIn(List<Byte> values) {
            addCriterion("month not in", values, "month");
            return (Criteria) this;
        }

        public Criteria andMonthBetween(Byte value1, Byte value2) {
            addCriterion("month between", value1, value2, "month");
            return (Criteria) this;
        }

        public Criteria andMonthNotBetween(Byte value1, Byte value2) {
            addCriterion("month not between", value1, value2, "month");
            return (Criteria) this;
        }

        public Criteria andUserNoIsNull() {
            addCriterion("user_no is null");
            return (Criteria) this;
        }

        public Criteria andUserNoIsNotNull() {
            addCriterion("user_no is not null");
            return (Criteria) this;
        }

        public Criteria andUserNoEqualTo(String value) {
            addCriterion("user_no =", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoNotEqualTo(String value) {
            addCriterion("user_no <>", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoGreaterThan(String value) {
            addCriterion("user_no >", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoGreaterThanOrEqualTo(String value) {
            addCriterion("user_no >=", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoLessThan(String value) {
            addCriterion("user_no <", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoLessThanOrEqualTo(String value) {
            addCriterion("user_no <=", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoLike(String value) {
            addCriterion("user_no like", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoNotLike(String value) {
            addCriterion("user_no not like", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoIn(List<String> values) {
            addCriterion("user_no in", values, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoNotIn(List<String> values) {
            addCriterion("user_no not in", values, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoBetween(String value1, String value2) {
            addCriterion("user_no between", value1, value2, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoNotBetween(String value1, String value2) {
            addCriterion("user_no not between", value1, value2, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("user_name is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("user_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("user_name =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("user_name <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("user_name >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_name >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("user_name <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("user_name <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("user_name like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("user_name not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("user_name in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("user_name not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("user_name between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("user_name not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andBoss1RemarkIsNull() {
            addCriterion("boss1_remark is null");
            return (Criteria) this;
        }

        public Criteria andBoss1RemarkIsNotNull() {
            addCriterion("boss1_remark is not null");
            return (Criteria) this;
        }

        public Criteria andBoss1RemarkEqualTo(String value) {
            addCriterion("boss1_remark =", value, "boss1Remark");
            return (Criteria) this;
        }

        public Criteria andBoss1RemarkNotEqualTo(String value) {
            addCriterion("boss1_remark <>", value, "boss1Remark");
            return (Criteria) this;
        }

        public Criteria andBoss1RemarkGreaterThan(String value) {
            addCriterion("boss1_remark >", value, "boss1Remark");
            return (Criteria) this;
        }

        public Criteria andBoss1RemarkGreaterThanOrEqualTo(String value) {
            addCriterion("boss1_remark >=", value, "boss1Remark");
            return (Criteria) this;
        }

        public Criteria andBoss1RemarkLessThan(String value) {
            addCriterion("boss1_remark <", value, "boss1Remark");
            return (Criteria) this;
        }

        public Criteria andBoss1RemarkLessThanOrEqualTo(String value) {
            addCriterion("boss1_remark <=", value, "boss1Remark");
            return (Criteria) this;
        }

        public Criteria andBoss1RemarkLike(String value) {
            addCriterion("boss1_remark like", value, "boss1Remark");
            return (Criteria) this;
        }

        public Criteria andBoss1RemarkNotLike(String value) {
            addCriterion("boss1_remark not like", value, "boss1Remark");
            return (Criteria) this;
        }

        public Criteria andBoss1RemarkIn(List<String> values) {
            addCriterion("boss1_remark in", values, "boss1Remark");
            return (Criteria) this;
        }

        public Criteria andBoss1RemarkNotIn(List<String> values) {
            addCriterion("boss1_remark not in", values, "boss1Remark");
            return (Criteria) this;
        }

        public Criteria andBoss1RemarkBetween(String value1, String value2) {
            addCriterion("boss1_remark between", value1, value2, "boss1Remark");
            return (Criteria) this;
        }

        public Criteria andBoss1RemarkNotBetween(String value1, String value2) {
            addCriterion("boss1_remark not between", value1, value2, "boss1Remark");
            return (Criteria) this;
        }

        public Criteria andBoss2RemarkIsNull() {
            addCriterion("boss2_remark is null");
            return (Criteria) this;
        }

        public Criteria andBoss2RemarkIsNotNull() {
            addCriterion("boss2_remark is not null");
            return (Criteria) this;
        }

        public Criteria andBoss2RemarkEqualTo(String value) {
            addCriterion("boss2_remark =", value, "boss2Remark");
            return (Criteria) this;
        }

        public Criteria andBoss2RemarkNotEqualTo(String value) {
            addCriterion("boss2_remark <>", value, "boss2Remark");
            return (Criteria) this;
        }

        public Criteria andBoss2RemarkGreaterThan(String value) {
            addCriterion("boss2_remark >", value, "boss2Remark");
            return (Criteria) this;
        }

        public Criteria andBoss2RemarkGreaterThanOrEqualTo(String value) {
            addCriterion("boss2_remark >=", value, "boss2Remark");
            return (Criteria) this;
        }

        public Criteria andBoss2RemarkLessThan(String value) {
            addCriterion("boss2_remark <", value, "boss2Remark");
            return (Criteria) this;
        }

        public Criteria andBoss2RemarkLessThanOrEqualTo(String value) {
            addCriterion("boss2_remark <=", value, "boss2Remark");
            return (Criteria) this;
        }

        public Criteria andBoss2RemarkLike(String value) {
            addCriterion("boss2_remark like", value, "boss2Remark");
            return (Criteria) this;
        }

        public Criteria andBoss2RemarkNotLike(String value) {
            addCriterion("boss2_remark not like", value, "boss2Remark");
            return (Criteria) this;
        }

        public Criteria andBoss2RemarkIn(List<String> values) {
            addCriterion("boss2_remark in", values, "boss2Remark");
            return (Criteria) this;
        }

        public Criteria andBoss2RemarkNotIn(List<String> values) {
            addCriterion("boss2_remark not in", values, "boss2Remark");
            return (Criteria) this;
        }

        public Criteria andBoss2RemarkBetween(String value1, String value2) {
            addCriterion("boss2_remark between", value1, value2, "boss2Remark");
            return (Criteria) this;
        }

        public Criteria andBoss2RemarkNotBetween(String value1, String value2) {
            addCriterion("boss2_remark not between", value1, value2, "boss2Remark");
            return (Criteria) this;
        }

        public Criteria andExGradeIsNull() {
            addCriterion("ex_grade is null");
            return (Criteria) this;
        }

        public Criteria andExGradeIsNotNull() {
            addCriterion("ex_grade is not null");
            return (Criteria) this;
        }

        public Criteria andExGradeEqualTo(Byte value) {
            addCriterion("ex_grade =", value, "exGrade");
            return (Criteria) this;
        }

        public Criteria andExGradeNotEqualTo(Byte value) {
            addCriterion("ex_grade <>", value, "exGrade");
            return (Criteria) this;
        }

        public Criteria andExGradeGreaterThan(Byte value) {
            addCriterion("ex_grade >", value, "exGrade");
            return (Criteria) this;
        }

        public Criteria andExGradeGreaterThanOrEqualTo(Byte value) {
            addCriterion("ex_grade >=", value, "exGrade");
            return (Criteria) this;
        }

        public Criteria andExGradeLessThan(Byte value) {
            addCriterion("ex_grade <", value, "exGrade");
            return (Criteria) this;
        }

        public Criteria andExGradeLessThanOrEqualTo(Byte value) {
            addCriterion("ex_grade <=", value, "exGrade");
            return (Criteria) this;
        }

        public Criteria andExGradeIn(List<Byte> values) {
            addCriterion("ex_grade in", values, "exGrade");
            return (Criteria) this;
        }

        public Criteria andExGradeNotIn(List<Byte> values) {
            addCriterion("ex_grade not in", values, "exGrade");
            return (Criteria) this;
        }

        public Criteria andExGradeBetween(Byte value1, Byte value2) {
            addCriterion("ex_grade between", value1, value2, "exGrade");
            return (Criteria) this;
        }

        public Criteria andExGradeNotBetween(Byte value1, Byte value2) {
            addCriterion("ex_grade not between", value1, value2, "exGrade");
            return (Criteria) this;
        }

        public Criteria andExReasonIsNull() {
            addCriterion("ex_reason is null");
            return (Criteria) this;
        }

        public Criteria andExReasonIsNotNull() {
            addCriterion("ex_reason is not null");
            return (Criteria) this;
        }

        public Criteria andExReasonEqualTo(String value) {
            addCriterion("ex_reason =", value, "exReason");
            return (Criteria) this;
        }

        public Criteria andExReasonNotEqualTo(String value) {
            addCriterion("ex_reason <>", value, "exReason");
            return (Criteria) this;
        }

        public Criteria andExReasonGreaterThan(String value) {
            addCriterion("ex_reason >", value, "exReason");
            return (Criteria) this;
        }

        public Criteria andExReasonGreaterThanOrEqualTo(String value) {
            addCriterion("ex_reason >=", value, "exReason");
            return (Criteria) this;
        }

        public Criteria andExReasonLessThan(String value) {
            addCriterion("ex_reason <", value, "exReason");
            return (Criteria) this;
        }

        public Criteria andExReasonLessThanOrEqualTo(String value) {
            addCriterion("ex_reason <=", value, "exReason");
            return (Criteria) this;
        }

        public Criteria andExReasonLike(String value) {
            addCriterion("ex_reason like", value, "exReason");
            return (Criteria) this;
        }

        public Criteria andExReasonNotLike(String value) {
            addCriterion("ex_reason not like", value, "exReason");
            return (Criteria) this;
        }

        public Criteria andExReasonIn(List<String> values) {
            addCriterion("ex_reason in", values, "exReason");
            return (Criteria) this;
        }

        public Criteria andExReasonNotIn(List<String> values) {
            addCriterion("ex_reason not in", values, "exReason");
            return (Criteria) this;
        }

        public Criteria andExReasonBetween(String value1, String value2) {
            addCriterion("ex_reason between", value1, value2, "exReason");
            return (Criteria) this;
        }

        public Criteria andExReasonNotBetween(String value1, String value2) {
            addCriterion("ex_reason not between", value1, value2, "exReason");
            return (Criteria) this;
        }

        public Criteria andFinalGradeIsNull() {
            addCriterion("final_grade is null");
            return (Criteria) this;
        }

        public Criteria andFinalGradeIsNotNull() {
            addCriterion("final_grade is not null");
            return (Criteria) this;
        }

        public Criteria andFinalGradeEqualTo(BigDecimal value) {
            addCriterion("final_grade =", value, "finalGrade");
            return (Criteria) this;
        }

        public Criteria andFinalGradeNotEqualTo(BigDecimal value) {
            addCriterion("final_grade <>", value, "finalGrade");
            return (Criteria) this;
        }

        public Criteria andFinalGradeGreaterThan(BigDecimal value) {
            addCriterion("final_grade >", value, "finalGrade");
            return (Criteria) this;
        }

        public Criteria andFinalGradeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("final_grade >=", value, "finalGrade");
            return (Criteria) this;
        }

        public Criteria andFinalGradeLessThan(BigDecimal value) {
            addCriterion("final_grade <", value, "finalGrade");
            return (Criteria) this;
        }

        public Criteria andFinalGradeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("final_grade <=", value, "finalGrade");
            return (Criteria) this;
        }

        public Criteria andFinalGradeIn(List<BigDecimal> values) {
            addCriterion("final_grade in", values, "finalGrade");
            return (Criteria) this;
        }

        public Criteria andFinalGradeNotIn(List<BigDecimal> values) {
            addCriterion("final_grade not in", values, "finalGrade");
            return (Criteria) this;
        }

        public Criteria andFinalGradeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("final_grade between", value1, value2, "finalGrade");
            return (Criteria) this;
        }

        public Criteria andFinalGradeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("final_grade not between", value1, value2, "finalGrade");
            return (Criteria) this;
        }

        public Criteria andBoss1NoIsNull() {
            addCriterion("boss1_no is null");
            return (Criteria) this;
        }

        public Criteria andBoss1NoIsNotNull() {
            addCriterion("boss1_no is not null");
            return (Criteria) this;
        }

        public Criteria andBoss1NoEqualTo(String value) {
            addCriterion("boss1_no =", value, "boss1No");
            return (Criteria) this;
        }

        public Criteria andBoss1NoNotEqualTo(String value) {
            addCriterion("boss1_no <>", value, "boss1No");
            return (Criteria) this;
        }

        public Criteria andBoss1NoGreaterThan(String value) {
            addCriterion("boss1_no >", value, "boss1No");
            return (Criteria) this;
        }

        public Criteria andBoss1NoGreaterThanOrEqualTo(String value) {
            addCriterion("boss1_no >=", value, "boss1No");
            return (Criteria) this;
        }

        public Criteria andBoss1NoLessThan(String value) {
            addCriterion("boss1_no <", value, "boss1No");
            return (Criteria) this;
        }

        public Criteria andBoss1NoLessThanOrEqualTo(String value) {
            addCriterion("boss1_no <=", value, "boss1No");
            return (Criteria) this;
        }

        public Criteria andBoss1NoLike(String value) {
            addCriterion("boss1_no like", value, "boss1No");
            return (Criteria) this;
        }

        public Criteria andBoss1NoNotLike(String value) {
            addCriterion("boss1_no not like", value, "boss1No");
            return (Criteria) this;
        }

        public Criteria andBoss1NoIn(List<String> values) {
            addCriterion("boss1_no in", values, "boss1No");
            return (Criteria) this;
        }

        public Criteria andBoss1NoNotIn(List<String> values) {
            addCriterion("boss1_no not in", values, "boss1No");
            return (Criteria) this;
        }

        public Criteria andBoss1NoBetween(String value1, String value2) {
            addCriterion("boss1_no between", value1, value2, "boss1No");
            return (Criteria) this;
        }

        public Criteria andBoss1NoNotBetween(String value1, String value2) {
            addCriterion("boss1_no not between", value1, value2, "boss1No");
            return (Criteria) this;
        }

        public Criteria andBoss1NameIsNull() {
            addCriterion("boss1_name is null");
            return (Criteria) this;
        }

        public Criteria andBoss1NameIsNotNull() {
            addCriterion("boss1_name is not null");
            return (Criteria) this;
        }

        public Criteria andBoss1NameEqualTo(String value) {
            addCriterion("boss1_name =", value, "boss1Name");
            return (Criteria) this;
        }

        public Criteria andBoss1NameNotEqualTo(String value) {
            addCriterion("boss1_name <>", value, "boss1Name");
            return (Criteria) this;
        }

        public Criteria andBoss1NameGreaterThan(String value) {
            addCriterion("boss1_name >", value, "boss1Name");
            return (Criteria) this;
        }

        public Criteria andBoss1NameGreaterThanOrEqualTo(String value) {
            addCriterion("boss1_name >=", value, "boss1Name");
            return (Criteria) this;
        }

        public Criteria andBoss1NameLessThan(String value) {
            addCriterion("boss1_name <", value, "boss1Name");
            return (Criteria) this;
        }

        public Criteria andBoss1NameLessThanOrEqualTo(String value) {
            addCriterion("boss1_name <=", value, "boss1Name");
            return (Criteria) this;
        }

        public Criteria andBoss1NameLike(String value) {
            addCriterion("boss1_name like", value, "boss1Name");
            return (Criteria) this;
        }

        public Criteria andBoss1NameNotLike(String value) {
            addCriterion("boss1_name not like", value, "boss1Name");
            return (Criteria) this;
        }

        public Criteria andBoss1NameIn(List<String> values) {
            addCriterion("boss1_name in", values, "boss1Name");
            return (Criteria) this;
        }

        public Criteria andBoss1NameNotIn(List<String> values) {
            addCriterion("boss1_name not in", values, "boss1Name");
            return (Criteria) this;
        }

        public Criteria andBoss1NameBetween(String value1, String value2) {
            addCriterion("boss1_name between", value1, value2, "boss1Name");
            return (Criteria) this;
        }

        public Criteria andBoss1NameNotBetween(String value1, String value2) {
            addCriterion("boss1_name not between", value1, value2, "boss1Name");
            return (Criteria) this;
        }

        public Criteria andBoss2NoIsNull() {
            addCriterion("boss2_no is null");
            return (Criteria) this;
        }

        public Criteria andBoss2NoIsNotNull() {
            addCriterion("boss2_no is not null");
            return (Criteria) this;
        }

        public Criteria andBoss2NoEqualTo(String value) {
            addCriterion("boss2_no =", value, "boss2No");
            return (Criteria) this;
        }

        public Criteria andBoss2NoNotEqualTo(String value) {
            addCriterion("boss2_no <>", value, "boss2No");
            return (Criteria) this;
        }

        public Criteria andBoss2NoGreaterThan(String value) {
            addCriterion("boss2_no >", value, "boss2No");
            return (Criteria) this;
        }

        public Criteria andBoss2NoGreaterThanOrEqualTo(String value) {
            addCriterion("boss2_no >=", value, "boss2No");
            return (Criteria) this;
        }

        public Criteria andBoss2NoLessThan(String value) {
            addCriterion("boss2_no <", value, "boss2No");
            return (Criteria) this;
        }

        public Criteria andBoss2NoLessThanOrEqualTo(String value) {
            addCriterion("boss2_no <=", value, "boss2No");
            return (Criteria) this;
        }

        public Criteria andBoss2NoLike(String value) {
            addCriterion("boss2_no like", value, "boss2No");
            return (Criteria) this;
        }

        public Criteria andBoss2NoNotLike(String value) {
            addCriterion("boss2_no not like", value, "boss2No");
            return (Criteria) this;
        }

        public Criteria andBoss2NoIn(List<String> values) {
            addCriterion("boss2_no in", values, "boss2No");
            return (Criteria) this;
        }

        public Criteria andBoss2NoNotIn(List<String> values) {
            addCriterion("boss2_no not in", values, "boss2No");
            return (Criteria) this;
        }

        public Criteria andBoss2NoBetween(String value1, String value2) {
            addCriterion("boss2_no between", value1, value2, "boss2No");
            return (Criteria) this;
        }

        public Criteria andBoss2NoNotBetween(String value1, String value2) {
            addCriterion("boss2_no not between", value1, value2, "boss2No");
            return (Criteria) this;
        }

        public Criteria andBoss2NameIsNull() {
            addCriterion("boss2_name is null");
            return (Criteria) this;
        }

        public Criteria andBoss2NameIsNotNull() {
            addCriterion("boss2_name is not null");
            return (Criteria) this;
        }

        public Criteria andBoss2NameEqualTo(String value) {
            addCriterion("boss2_name =", value, "boss2Name");
            return (Criteria) this;
        }

        public Criteria andBoss2NameNotEqualTo(String value) {
            addCriterion("boss2_name <>", value, "boss2Name");
            return (Criteria) this;
        }

        public Criteria andBoss2NameGreaterThan(String value) {
            addCriterion("boss2_name >", value, "boss2Name");
            return (Criteria) this;
        }

        public Criteria andBoss2NameGreaterThanOrEqualTo(String value) {
            addCriterion("boss2_name >=", value, "boss2Name");
            return (Criteria) this;
        }

        public Criteria andBoss2NameLessThan(String value) {
            addCriterion("boss2_name <", value, "boss2Name");
            return (Criteria) this;
        }

        public Criteria andBoss2NameLessThanOrEqualTo(String value) {
            addCriterion("boss2_name <=", value, "boss2Name");
            return (Criteria) this;
        }

        public Criteria andBoss2NameLike(String value) {
            addCriterion("boss2_name like", value, "boss2Name");
            return (Criteria) this;
        }

        public Criteria andBoss2NameNotLike(String value) {
            addCriterion("boss2_name not like", value, "boss2Name");
            return (Criteria) this;
        }

        public Criteria andBoss2NameIn(List<String> values) {
            addCriterion("boss2_name in", values, "boss2Name");
            return (Criteria) this;
        }

        public Criteria andBoss2NameNotIn(List<String> values) {
            addCriterion("boss2_name not in", values, "boss2Name");
            return (Criteria) this;
        }

        public Criteria andBoss2NameBetween(String value1, String value2) {
            addCriterion("boss2_name between", value1, value2, "boss2Name");
            return (Criteria) this;
        }

        public Criteria andBoss2NameNotBetween(String value1, String value2) {
            addCriterion("boss2_name not between", value1, value2, "boss2Name");
            return (Criteria) this;
        }

        public Criteria andHrNoIsNull() {
            addCriterion("hr_no is null");
            return (Criteria) this;
        }

        public Criteria andHrNoIsNotNull() {
            addCriterion("hr_no is not null");
            return (Criteria) this;
        }

        public Criteria andHrNoEqualTo(String value) {
            addCriterion("hr_no =", value, "hrNo");
            return (Criteria) this;
        }

        public Criteria andHrNoNotEqualTo(String value) {
            addCriterion("hr_no <>", value, "hrNo");
            return (Criteria) this;
        }

        public Criteria andHrNoGreaterThan(String value) {
            addCriterion("hr_no >", value, "hrNo");
            return (Criteria) this;
        }

        public Criteria andHrNoGreaterThanOrEqualTo(String value) {
            addCriterion("hr_no >=", value, "hrNo");
            return (Criteria) this;
        }

        public Criteria andHrNoLessThan(String value) {
            addCriterion("hr_no <", value, "hrNo");
            return (Criteria) this;
        }

        public Criteria andHrNoLessThanOrEqualTo(String value) {
            addCriterion("hr_no <=", value, "hrNo");
            return (Criteria) this;
        }

        public Criteria andHrNoLike(String value) {
            addCriterion("hr_no like", value, "hrNo");
            return (Criteria) this;
        }

        public Criteria andHrNoNotLike(String value) {
            addCriterion("hr_no not like", value, "hrNo");
            return (Criteria) this;
        }

        public Criteria andHrNoIn(List<String> values) {
            addCriterion("hr_no in", values, "hrNo");
            return (Criteria) this;
        }

        public Criteria andHrNoNotIn(List<String> values) {
            addCriterion("hr_no not in", values, "hrNo");
            return (Criteria) this;
        }

        public Criteria andHrNoBetween(String value1, String value2) {
            addCriterion("hr_no between", value1, value2, "hrNo");
            return (Criteria) this;
        }

        public Criteria andHrNoNotBetween(String value1, String value2) {
            addCriterion("hr_no not between", value1, value2, "hrNo");
            return (Criteria) this;
        }

        public Criteria andHrNameIsNull() {
            addCriterion("hr_name is null");
            return (Criteria) this;
        }

        public Criteria andHrNameIsNotNull() {
            addCriterion("hr_name is not null");
            return (Criteria) this;
        }

        public Criteria andHrNameEqualTo(String value) {
            addCriterion("hr_name =", value, "hrName");
            return (Criteria) this;
        }

        public Criteria andHrNameNotEqualTo(String value) {
            addCriterion("hr_name <>", value, "hrName");
            return (Criteria) this;
        }

        public Criteria andHrNameGreaterThan(String value) {
            addCriterion("hr_name >", value, "hrName");
            return (Criteria) this;
        }

        public Criteria andHrNameGreaterThanOrEqualTo(String value) {
            addCriterion("hr_name >=", value, "hrName");
            return (Criteria) this;
        }

        public Criteria andHrNameLessThan(String value) {
            addCriterion("hr_name <", value, "hrName");
            return (Criteria) this;
        }

        public Criteria andHrNameLessThanOrEqualTo(String value) {
            addCriterion("hr_name <=", value, "hrName");
            return (Criteria) this;
        }

        public Criteria andHrNameLike(String value) {
            addCriterion("hr_name like", value, "hrName");
            return (Criteria) this;
        }

        public Criteria andHrNameNotLike(String value) {
            addCriterion("hr_name not like", value, "hrName");
            return (Criteria) this;
        }

        public Criteria andHrNameIn(List<String> values) {
            addCriterion("hr_name in", values, "hrName");
            return (Criteria) this;
        }

        public Criteria andHrNameNotIn(List<String> values) {
            addCriterion("hr_name not in", values, "hrName");
            return (Criteria) this;
        }

        public Criteria andHrNameBetween(String value1, String value2) {
            addCriterion("hr_name between", value1, value2, "hrName");
            return (Criteria) this;
        }

        public Criteria andHrNameNotBetween(String value1, String value2) {
            addCriterion("hr_name not between", value1, value2, "hrName");
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