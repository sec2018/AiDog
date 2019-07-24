package com.sec.aidog.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AnimalillExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AnimalillExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNumIsNull() {
            addCriterion("num is null");
            return (Criteria) this;
        }

        public Criteria andNumIsNotNull() {
            addCriterion("num is not null");
            return (Criteria) this;
        }

        public Criteria andNumEqualTo(String value) {
            addCriterion("num =", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotEqualTo(String value) {
            addCriterion("num <>", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThan(String value) {
            addCriterion("num >", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThanOrEqualTo(String value) {
            addCriterion("num >=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThan(String value) {
            addCriterion("num <", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThanOrEqualTo(String value) {
            addCriterion("num <=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLike(String value) {
            addCriterion("num like", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotLike(String value) {
            addCriterion("num not like", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumIn(List<String> values) {
            addCriterion("num in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotIn(List<String> values) {
            addCriterion("num not in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumBetween(String value1, String value2) {
            addCriterion("num between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotBetween(String value1, String value2) {
            addCriterion("num not between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andKillplaceIsNull() {
            addCriterion("killplace is null");
            return (Criteria) this;
        }

        public Criteria andKillplaceIsNotNull() {
            addCriterion("killplace is not null");
            return (Criteria) this;
        }

        public Criteria andKillplaceEqualTo(String value) {
            addCriterion("killplace =", value, "killplace");
            return (Criteria) this;
        }

        public Criteria andKillplaceNotEqualTo(String value) {
            addCriterion("killplace <>", value, "killplace");
            return (Criteria) this;
        }

        public Criteria andKillplaceGreaterThan(String value) {
            addCriterion("killplace >", value, "killplace");
            return (Criteria) this;
        }

        public Criteria andKillplaceGreaterThanOrEqualTo(String value) {
            addCriterion("killplace >=", value, "killplace");
            return (Criteria) this;
        }

        public Criteria andKillplaceLessThan(String value) {
            addCriterion("killplace <", value, "killplace");
            return (Criteria) this;
        }

        public Criteria andKillplaceLessThanOrEqualTo(String value) {
            addCriterion("killplace <=", value, "killplace");
            return (Criteria) this;
        }

        public Criteria andKillplaceLike(String value) {
            addCriterion("killplace like", value, "killplace");
            return (Criteria) this;
        }

        public Criteria andKillplaceNotLike(String value) {
            addCriterion("killplace not like", value, "killplace");
            return (Criteria) this;
        }

        public Criteria andKillplaceIn(List<String> values) {
            addCriterion("killplace in", values, "killplace");
            return (Criteria) this;
        }

        public Criteria andKillplaceNotIn(List<String> values) {
            addCriterion("killplace not in", values, "killplace");
            return (Criteria) this;
        }

        public Criteria andKillplaceBetween(String value1, String value2) {
            addCriterion("killplace between", value1, value2, "killplace");
            return (Criteria) this;
        }

        public Criteria andKillplaceNotBetween(String value1, String value2) {
            addCriterion("killplace not between", value1, value2, "killplace");
            return (Criteria) this;
        }

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("code not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andSexIsNull() {
            addCriterion("sex is null");
            return (Criteria) this;
        }

        public Criteria andSexIsNotNull() {
            addCriterion("sex is not null");
            return (Criteria) this;
        }

        public Criteria andSexEqualTo(String value) {
            addCriterion("sex =", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(String value) {
            addCriterion("sex <>", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(String value) {
            addCriterion("sex >", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(String value) {
            addCriterion("sex >=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(String value) {
            addCriterion("sex <", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(String value) {
            addCriterion("sex <=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLike(String value) {
            addCriterion("sex like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotLike(String value) {
            addCriterion("sex not like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexIn(List<String> values) {
            addCriterion("sex in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<String> values) {
            addCriterion("sex not in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexBetween(String value1, String value2) {
            addCriterion("sex between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(String value1, String value2) {
            addCriterion("sex not between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andAgeIsNull() {
            addCriterion("age is null");
            return (Criteria) this;
        }

        public Criteria andAgeIsNotNull() {
            addCriterion("age is not null");
            return (Criteria) this;
        }

        public Criteria andAgeEqualTo(Integer value) {
            addCriterion("age =", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeNotEqualTo(Integer value) {
            addCriterion("age <>", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeGreaterThan(Integer value) {
            addCriterion("age >", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeGreaterThanOrEqualTo(Integer value) {
            addCriterion("age >=", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeLessThan(Integer value) {
            addCriterion("age <", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeLessThanOrEqualTo(Integer value) {
            addCriterion("age <=", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeIn(List<Integer> values) {
            addCriterion("age in", values, "age");
            return (Criteria) this;
        }

        public Criteria andAgeNotIn(List<Integer> values) {
            addCriterion("age not in", values, "age");
            return (Criteria) this;
        }

        public Criteria andAgeBetween(Integer value1, Integer value2) {
            addCriterion("age between", value1, value2, "age");
            return (Criteria) this;
        }

        public Criteria andAgeNotBetween(Integer value1, Integer value2) {
            addCriterion("age not between", value1, value2, "age");
            return (Criteria) this;
        }

        public Criteria andEyecheckIsNull() {
            addCriterion("eyecheck is null");
            return (Criteria) this;
        }

        public Criteria andEyecheckIsNotNull() {
            addCriterion("eyecheck is not null");
            return (Criteria) this;
        }

        public Criteria andEyecheckEqualTo(String value) {
            addCriterion("eyecheck =", value, "eyecheck");
            return (Criteria) this;
        }

        public Criteria andEyecheckNotEqualTo(String value) {
            addCriterion("eyecheck <>", value, "eyecheck");
            return (Criteria) this;
        }

        public Criteria andEyecheckGreaterThan(String value) {
            addCriterion("eyecheck >", value, "eyecheck");
            return (Criteria) this;
        }

        public Criteria andEyecheckGreaterThanOrEqualTo(String value) {
            addCriterion("eyecheck >=", value, "eyecheck");
            return (Criteria) this;
        }

        public Criteria andEyecheckLessThan(String value) {
            addCriterion("eyecheck <", value, "eyecheck");
            return (Criteria) this;
        }

        public Criteria andEyecheckLessThanOrEqualTo(String value) {
            addCriterion("eyecheck <=", value, "eyecheck");
            return (Criteria) this;
        }

        public Criteria andEyecheckLike(String value) {
            addCriterion("eyecheck like", value, "eyecheck");
            return (Criteria) this;
        }

        public Criteria andEyecheckNotLike(String value) {
            addCriterion("eyecheck not like", value, "eyecheck");
            return (Criteria) this;
        }

        public Criteria andEyecheckIn(List<String> values) {
            addCriterion("eyecheck in", values, "eyecheck");
            return (Criteria) this;
        }

        public Criteria andEyecheckNotIn(List<String> values) {
            addCriterion("eyecheck not in", values, "eyecheck");
            return (Criteria) this;
        }

        public Criteria andEyecheckBetween(String value1, String value2) {
            addCriterion("eyecheck between", value1, value2, "eyecheck");
            return (Criteria) this;
        }

        public Criteria andEyecheckNotBetween(String value1, String value2) {
            addCriterion("eyecheck not between", value1, value2, "eyecheck");
            return (Criteria) this;
        }

        public Criteria andPcrIsNull() {
            addCriterion("pcr is null");
            return (Criteria) this;
        }

        public Criteria andPcrIsNotNull() {
            addCriterion("pcr is not null");
            return (Criteria) this;
        }

        public Criteria andPcrEqualTo(String value) {
            addCriterion("pcr =", value, "pcr");
            return (Criteria) this;
        }

        public Criteria andPcrNotEqualTo(String value) {
            addCriterion("pcr <>", value, "pcr");
            return (Criteria) this;
        }

        public Criteria andPcrGreaterThan(String value) {
            addCriterion("pcr >", value, "pcr");
            return (Criteria) this;
        }

        public Criteria andPcrGreaterThanOrEqualTo(String value) {
            addCriterion("pcr >=", value, "pcr");
            return (Criteria) this;
        }

        public Criteria andPcrLessThan(String value) {
            addCriterion("pcr <", value, "pcr");
            return (Criteria) this;
        }

        public Criteria andPcrLessThanOrEqualTo(String value) {
            addCriterion("pcr <=", value, "pcr");
            return (Criteria) this;
        }

        public Criteria andPcrLike(String value) {
            addCriterion("pcr like", value, "pcr");
            return (Criteria) this;
        }

        public Criteria andPcrNotLike(String value) {
            addCriterion("pcr not like", value, "pcr");
            return (Criteria) this;
        }

        public Criteria andPcrIn(List<String> values) {
            addCriterion("pcr in", values, "pcr");
            return (Criteria) this;
        }

        public Criteria andPcrNotIn(List<String> values) {
            addCriterion("pcr not in", values, "pcr");
            return (Criteria) this;
        }

        public Criteria andPcrBetween(String value1, String value2) {
            addCriterion("pcr between", value1, value2, "pcr");
            return (Criteria) this;
        }

        public Criteria andPcrNotBetween(String value1, String value2) {
            addCriterion("pcr not between", value1, value2, "pcr");
            return (Criteria) this;
        }

        public Criteria andCheckresIsNull() {
            addCriterion("checkres is null");
            return (Criteria) this;
        }

        public Criteria andCheckresIsNotNull() {
            addCriterion("checkres is not null");
            return (Criteria) this;
        }

        public Criteria andCheckresEqualTo(String value) {
            addCriterion("checkres =", value, "checkres");
            return (Criteria) this;
        }

        public Criteria andCheckresNotEqualTo(String value) {
            addCriterion("checkres <>", value, "checkres");
            return (Criteria) this;
        }

        public Criteria andCheckresGreaterThan(String value) {
            addCriterion("checkres >", value, "checkres");
            return (Criteria) this;
        }

        public Criteria andCheckresGreaterThanOrEqualTo(String value) {
            addCriterion("checkres >=", value, "checkres");
            return (Criteria) this;
        }

        public Criteria andCheckresLessThan(String value) {
            addCriterion("checkres <", value, "checkres");
            return (Criteria) this;
        }

        public Criteria andCheckresLessThanOrEqualTo(String value) {
            addCriterion("checkres <=", value, "checkres");
            return (Criteria) this;
        }

        public Criteria andCheckresLike(String value) {
            addCriterion("checkres like", value, "checkres");
            return (Criteria) this;
        }

        public Criteria andCheckresNotLike(String value) {
            addCriterion("checkres not like", value, "checkres");
            return (Criteria) this;
        }

        public Criteria andCheckresIn(List<String> values) {
            addCriterion("checkres in", values, "checkres");
            return (Criteria) this;
        }

        public Criteria andCheckresNotIn(List<String> values) {
            addCriterion("checkres not in", values, "checkres");
            return (Criteria) this;
        }

        public Criteria andCheckresBetween(String value1, String value2) {
            addCriterion("checkres between", value1, value2, "checkres");
            return (Criteria) this;
        }

        public Criteria andCheckresNotBetween(String value1, String value2) {
            addCriterion("checkres not between", value1, value2, "checkres");
            return (Criteria) this;
        }

        public Criteria andCheckpersonIsNull() {
            addCriterion("checkperson is null");
            return (Criteria) this;
        }

        public Criteria andCheckpersonIsNotNull() {
            addCriterion("checkperson is not null");
            return (Criteria) this;
        }

        public Criteria andCheckpersonEqualTo(String value) {
            addCriterion("checkperson =", value, "checkperson");
            return (Criteria) this;
        }

        public Criteria andCheckpersonNotEqualTo(String value) {
            addCriterion("checkperson <>", value, "checkperson");
            return (Criteria) this;
        }

        public Criteria andCheckpersonGreaterThan(String value) {
            addCriterion("checkperson >", value, "checkperson");
            return (Criteria) this;
        }

        public Criteria andCheckpersonGreaterThanOrEqualTo(String value) {
            addCriterion("checkperson >=", value, "checkperson");
            return (Criteria) this;
        }

        public Criteria andCheckpersonLessThan(String value) {
            addCriterion("checkperson <", value, "checkperson");
            return (Criteria) this;
        }

        public Criteria andCheckpersonLessThanOrEqualTo(String value) {
            addCriterion("checkperson <=", value, "checkperson");
            return (Criteria) this;
        }

        public Criteria andCheckpersonLike(String value) {
            addCriterion("checkperson like", value, "checkperson");
            return (Criteria) this;
        }

        public Criteria andCheckpersonNotLike(String value) {
            addCriterion("checkperson not like", value, "checkperson");
            return (Criteria) this;
        }

        public Criteria andCheckpersonIn(List<String> values) {
            addCriterion("checkperson in", values, "checkperson");
            return (Criteria) this;
        }

        public Criteria andCheckpersonNotIn(List<String> values) {
            addCriterion("checkperson not in", values, "checkperson");
            return (Criteria) this;
        }

        public Criteria andCheckpersonBetween(String value1, String value2) {
            addCriterion("checkperson between", value1, value2, "checkperson");
            return (Criteria) this;
        }

        public Criteria andCheckpersonNotBetween(String value1, String value2) {
            addCriterion("checkperson not between", value1, value2, "checkperson");
            return (Criteria) this;
        }

        public Criteria andCheckdateIsNull() {
            addCriterion("checkdate is null");
            return (Criteria) this;
        }

        public Criteria andCheckdateIsNotNull() {
            addCriterion("checkdate is not null");
            return (Criteria) this;
        }

        public Criteria andCheckdateEqualTo(Date value) {
            addCriterion("checkdate =", value, "checkdate");
            return (Criteria) this;
        }

        public Criteria andCheckdateNotEqualTo(Date value) {
            addCriterion("checkdate <>", value, "checkdate");
            return (Criteria) this;
        }

        public Criteria andCheckdateGreaterThan(Date value) {
            addCriterion("checkdate >", value, "checkdate");
            return (Criteria) this;
        }

        public Criteria andCheckdateGreaterThanOrEqualTo(Date value) {
            addCriterion("checkdate >=", value, "checkdate");
            return (Criteria) this;
        }

        public Criteria andCheckdateLessThan(Date value) {
            addCriterion("checkdate <", value, "checkdate");
            return (Criteria) this;
        }

        public Criteria andCheckdateLessThanOrEqualTo(Date value) {
            addCriterion("checkdate <=", value, "checkdate");
            return (Criteria) this;
        }

        public Criteria andCheckdateIn(List<Date> values) {
            addCriterion("checkdate in", values, "checkdate");
            return (Criteria) this;
        }

        public Criteria andCheckdateNotIn(List<Date> values) {
            addCriterion("checkdate not in", values, "checkdate");
            return (Criteria) this;
        }

        public Criteria andCheckdateBetween(Date value1, Date value2) {
            addCriterion("checkdate between", value1, value2, "checkdate");
            return (Criteria) this;
        }

        public Criteria andCheckdateNotBetween(Date value1, Date value2) {
            addCriterion("checkdate not between", value1, value2, "checkdate");
            return (Criteria) this;
        }

        public Criteria andNoteIsNull() {
            addCriterion("note is null");
            return (Criteria) this;
        }

        public Criteria andNoteIsNotNull() {
            addCriterion("note is not null");
            return (Criteria) this;
        }

        public Criteria andNoteEqualTo(String value) {
            addCriterion("note =", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotEqualTo(String value) {
            addCriterion("note <>", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThan(String value) {
            addCriterion("note >", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThanOrEqualTo(String value) {
            addCriterion("note >=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThan(String value) {
            addCriterion("note <", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThanOrEqualTo(String value) {
            addCriterion("note <=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLike(String value) {
            addCriterion("note like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotLike(String value) {
            addCriterion("note not like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteIn(List<String> values) {
            addCriterion("note in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotIn(List<String> values) {
            addCriterion("note not in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteBetween(String value1, String value2) {
            addCriterion("note between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotBetween(String value1, String value2) {
            addCriterion("note not between", value1, value2, "note");
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