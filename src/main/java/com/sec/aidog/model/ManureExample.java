package com.sec.aidog.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ManureExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ManureExample() {
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

        public Criteria andDogmanureCodeIsNull() {
            addCriterion("dogmanure_code is null");
            return (Criteria) this;
        }

        public Criteria andDogmanureCodeIsNotNull() {
            addCriterion("dogmanure_code is not null");
            return (Criteria) this;
        }

        public Criteria andDogmanureCodeEqualTo(String value) {
            addCriterion("dogmanure_code =", value, "dogmanureCode");
            return (Criteria) this;
        }

        public Criteria andDogmanureCodeNotEqualTo(String value) {
            addCriterion("dogmanure_code <>", value, "dogmanureCode");
            return (Criteria) this;
        }

        public Criteria andDogmanureCodeGreaterThan(String value) {
            addCriterion("dogmanure_code >", value, "dogmanureCode");
            return (Criteria) this;
        }

        public Criteria andDogmanureCodeGreaterThanOrEqualTo(String value) {
            addCriterion("dogmanure_code >=", value, "dogmanureCode");
            return (Criteria) this;
        }

        public Criteria andDogmanureCodeLessThan(String value) {
            addCriterion("dogmanure_code <", value, "dogmanureCode");
            return (Criteria) this;
        }

        public Criteria andDogmanureCodeLessThanOrEqualTo(String value) {
            addCriterion("dogmanure_code <=", value, "dogmanureCode");
            return (Criteria) this;
        }

        public Criteria andDogmanureCodeLike(String value) {
            addCriterion("dogmanure_code like", value, "dogmanureCode");
            return (Criteria) this;
        }

        public Criteria andDogmanureCodeNotLike(String value) {
            addCriterion("dogmanure_code not like", value, "dogmanureCode");
            return (Criteria) this;
        }

        public Criteria andDogmanureCodeIn(List<String> values) {
            addCriterion("dogmanure_code in", values, "dogmanureCode");
            return (Criteria) this;
        }

        public Criteria andDogmanureCodeNotIn(List<String> values) {
            addCriterion("dogmanure_code not in", values, "dogmanureCode");
            return (Criteria) this;
        }

        public Criteria andDogmanureCodeBetween(String value1, String value2) {
            addCriterion("dogmanure_code between", value1, value2, "dogmanureCode");
            return (Criteria) this;
        }

        public Criteria andDogmanureCodeNotBetween(String value1, String value2) {
            addCriterion("dogmanure_code not between", value1, value2, "dogmanureCode");
            return (Criteria) this;
        }

        public Criteria andDogownerIdIsNull() {
            addCriterion("dogowner_id is null");
            return (Criteria) this;
        }

        public Criteria andDogownerIdIsNotNull() {
            addCriterion("dogowner_id is not null");
            return (Criteria) this;
        }

        public Criteria andDogownerIdEqualTo(Integer value) {
            addCriterion("dogowner_id =", value, "dogownerId");
            return (Criteria) this;
        }

        public Criteria andDogownerIdNotEqualTo(Integer value) {
            addCriterion("dogowner_id <>", value, "dogownerId");
            return (Criteria) this;
        }

        public Criteria andDogownerIdGreaterThan(Integer value) {
            addCriterion("dogowner_id >", value, "dogownerId");
            return (Criteria) this;
        }

        public Criteria andDogownerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("dogowner_id >=", value, "dogownerId");
            return (Criteria) this;
        }

        public Criteria andDogownerIdLessThan(Integer value) {
            addCriterion("dogowner_id <", value, "dogownerId");
            return (Criteria) this;
        }

        public Criteria andDogownerIdLessThanOrEqualTo(Integer value) {
            addCriterion("dogowner_id <=", value, "dogownerId");
            return (Criteria) this;
        }

        public Criteria andDogownerIdIn(List<Integer> values) {
            addCriterion("dogowner_id in", values, "dogownerId");
            return (Criteria) this;
        }

        public Criteria andDogownerIdNotIn(List<Integer> values) {
            addCriterion("dogowner_id not in", values, "dogownerId");
            return (Criteria) this;
        }

        public Criteria andDogownerIdBetween(Integer value1, Integer value2) {
            addCriterion("dogowner_id between", value1, value2, "dogownerId");
            return (Criteria) this;
        }

        public Criteria andDogownerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("dogowner_id not between", value1, value2, "dogownerId");
            return (Criteria) this;
        }

        public Criteria andDogIdIsNull() {
            addCriterion("dog_id is null");
            return (Criteria) this;
        }

        public Criteria andDogIdIsNotNull() {
            addCriterion("dog_id is not null");
            return (Criteria) this;
        }

        public Criteria andDogIdEqualTo(Integer value) {
            addCriterion("dog_id =", value, "dogId");
            return (Criteria) this;
        }

        public Criteria andDogIdNotEqualTo(Integer value) {
            addCriterion("dog_id <>", value, "dogId");
            return (Criteria) this;
        }

        public Criteria andDogIdGreaterThan(Integer value) {
            addCriterion("dog_id >", value, "dogId");
            return (Criteria) this;
        }

        public Criteria andDogIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("dog_id >=", value, "dogId");
            return (Criteria) this;
        }

        public Criteria andDogIdLessThan(Integer value) {
            addCriterion("dog_id <", value, "dogId");
            return (Criteria) this;
        }

        public Criteria andDogIdLessThanOrEqualTo(Integer value) {
            addCriterion("dog_id <=", value, "dogId");
            return (Criteria) this;
        }

        public Criteria andDogIdIn(List<Integer> values) {
            addCriterion("dog_id in", values, "dogId");
            return (Criteria) this;
        }

        public Criteria andDogIdNotIn(List<Integer> values) {
            addCriterion("dog_id not in", values, "dogId");
            return (Criteria) this;
        }

        public Criteria andDogIdBetween(Integer value1, Integer value2) {
            addCriterion("dog_id between", value1, value2, "dogId");
            return (Criteria) this;
        }

        public Criteria andDogIdNotBetween(Integer value1, Integer value2) {
            addCriterion("dog_id not between", value1, value2, "dogId");
            return (Criteria) this;
        }

        public Criteria andCollectionDateIsNull() {
            addCriterion("collection_date is null");
            return (Criteria) this;
        }

        public Criteria andCollectionDateIsNotNull() {
            addCriterion("collection_date is not null");
            return (Criteria) this;
        }

        public Criteria andCollectionDateEqualTo(Date value) {
            addCriterion("collection_date =", value, "collectionDate");
            return (Criteria) this;
        }

        public Criteria andCollectionDateNotEqualTo(Date value) {
            addCriterion("collection_date <>", value, "collectionDate");
            return (Criteria) this;
        }

        public Criteria andCollectionDateGreaterThan(Date value) {
            addCriterion("collection_date >", value, "collectionDate");
            return (Criteria) this;
        }

        public Criteria andCollectionDateGreaterThanOrEqualTo(Date value) {
            addCriterion("collection_date >=", value, "collectionDate");
            return (Criteria) this;
        }

        public Criteria andCollectionDateLessThan(Date value) {
            addCriterion("collection_date <", value, "collectionDate");
            return (Criteria) this;
        }

        public Criteria andCollectionDateLessThanOrEqualTo(Date value) {
            addCriterion("collection_date <=", value, "collectionDate");
            return (Criteria) this;
        }

        public Criteria andCollectionDateIn(List<Date> values) {
            addCriterion("collection_date in", values, "collectionDate");
            return (Criteria) this;
        }

        public Criteria andCollectionDateNotIn(List<Date> values) {
            addCriterion("collection_date not in", values, "collectionDate");
            return (Criteria) this;
        }

        public Criteria andCollectionDateBetween(Date value1, Date value2) {
            addCriterion("collection_date between", value1, value2, "collectionDate");
            return (Criteria) this;
        }

        public Criteria andCollectionDateNotBetween(Date value1, Date value2) {
            addCriterion("collection_date not between", value1, value2, "collectionDate");
            return (Criteria) this;
        }

        public Criteria andCollectionPersonIsNull() {
            addCriterion("collection_person is null");
            return (Criteria) this;
        }

        public Criteria andCollectionPersonIsNotNull() {
            addCriterion("collection_person is not null");
            return (Criteria) this;
        }

        public Criteria andCollectionPersonEqualTo(String value) {
            addCriterion("collection_person =", value, "collectionPerson");
            return (Criteria) this;
        }

        public Criteria andCollectionPersonNotEqualTo(String value) {
            addCriterion("collection_person <>", value, "collectionPerson");
            return (Criteria) this;
        }

        public Criteria andCollectionPersonGreaterThan(String value) {
            addCriterion("collection_person >", value, "collectionPerson");
            return (Criteria) this;
        }

        public Criteria andCollectionPersonGreaterThanOrEqualTo(String value) {
            addCriterion("collection_person >=", value, "collectionPerson");
            return (Criteria) this;
        }

        public Criteria andCollectionPersonLessThan(String value) {
            addCriterion("collection_person <", value, "collectionPerson");
            return (Criteria) this;
        }

        public Criteria andCollectionPersonLessThanOrEqualTo(String value) {
            addCriterion("collection_person <=", value, "collectionPerson");
            return (Criteria) this;
        }

        public Criteria andCollectionPersonLike(String value) {
            addCriterion("collection_person like", value, "collectionPerson");
            return (Criteria) this;
        }

        public Criteria andCollectionPersonNotLike(String value) {
            addCriterion("collection_person not like", value, "collectionPerson");
            return (Criteria) this;
        }

        public Criteria andCollectionPersonIn(List<String> values) {
            addCriterion("collection_person in", values, "collectionPerson");
            return (Criteria) this;
        }

        public Criteria andCollectionPersonNotIn(List<String> values) {
            addCriterion("collection_person not in", values, "collectionPerson");
            return (Criteria) this;
        }

        public Criteria andCollectionPersonBetween(String value1, String value2) {
            addCriterion("collection_person between", value1, value2, "collectionPerson");
            return (Criteria) this;
        }

        public Criteria andCollectionPersonNotBetween(String value1, String value2) {
            addCriterion("collection_person not between", value1, value2, "collectionPerson");
            return (Criteria) this;
        }

        public Criteria andDistrictcodeIsNull() {
            addCriterion("districtcode is null");
            return (Criteria) this;
        }

        public Criteria andDistrictcodeIsNotNull() {
            addCriterion("districtcode is not null");
            return (Criteria) this;
        }

        public Criteria andDistrictcodeEqualTo(String value) {
            addCriterion("districtcode =", value, "districtcode");
            return (Criteria) this;
        }

        public Criteria andDistrictcodeNotEqualTo(String value) {
            addCriterion("districtcode <>", value, "districtcode");
            return (Criteria) this;
        }

        public Criteria andDistrictcodeGreaterThan(String value) {
            addCriterion("districtcode >", value, "districtcode");
            return (Criteria) this;
        }

        public Criteria andDistrictcodeGreaterThanOrEqualTo(String value) {
            addCriterion("districtcode >=", value, "districtcode");
            return (Criteria) this;
        }

        public Criteria andDistrictcodeLessThan(String value) {
            addCriterion("districtcode <", value, "districtcode");
            return (Criteria) this;
        }

        public Criteria andDistrictcodeLessThanOrEqualTo(String value) {
            addCriterion("districtcode <=", value, "districtcode");
            return (Criteria) this;
        }

        public Criteria andDistrictcodeLike(String value) {
            addCriterion("districtcode like", value, "districtcode");
            return (Criteria) this;
        }

        public Criteria andDistrictcodeNotLike(String value) {
            addCriterion("districtcode not like", value, "districtcode");
            return (Criteria) this;
        }

        public Criteria andDistrictcodeIn(List<String> values) {
            addCriterion("districtcode in", values, "districtcode");
            return (Criteria) this;
        }

        public Criteria andDistrictcodeNotIn(List<String> values) {
            addCriterion("districtcode not in", values, "districtcode");
            return (Criteria) this;
        }

        public Criteria andDistrictcodeBetween(String value1, String value2) {
            addCriterion("districtcode between", value1, value2, "districtcode");
            return (Criteria) this;
        }

        public Criteria andDistrictcodeNotBetween(String value1, String value2) {
            addCriterion("districtcode not between", value1, value2, "districtcode");
            return (Criteria) this;
        }

        public Criteria andTestingDateIsNull() {
            addCriterion("testing_date is null");
            return (Criteria) this;
        }

        public Criteria andTestingDateIsNotNull() {
            addCriterion("testing_date is not null");
            return (Criteria) this;
        }

        public Criteria andTestingDateEqualTo(Date value) {
            addCriterion("testing_date =", value, "testingDate");
            return (Criteria) this;
        }

        public Criteria andTestingDateNotEqualTo(Date value) {
            addCriterion("testing_date <>", value, "testingDate");
            return (Criteria) this;
        }

        public Criteria andTestingDateGreaterThan(Date value) {
            addCriterion("testing_date >", value, "testingDate");
            return (Criteria) this;
        }

        public Criteria andTestingDateGreaterThanOrEqualTo(Date value) {
            addCriterion("testing_date >=", value, "testingDate");
            return (Criteria) this;
        }

        public Criteria andTestingDateLessThan(Date value) {
            addCriterion("testing_date <", value, "testingDate");
            return (Criteria) this;
        }

        public Criteria andTestingDateLessThanOrEqualTo(Date value) {
            addCriterion("testing_date <=", value, "testingDate");
            return (Criteria) this;
        }

        public Criteria andTestingDateIn(List<Date> values) {
            addCriterion("testing_date in", values, "testingDate");
            return (Criteria) this;
        }

        public Criteria andTestingDateNotIn(List<Date> values) {
            addCriterion("testing_date not in", values, "testingDate");
            return (Criteria) this;
        }

        public Criteria andTestingDateBetween(Date value1, Date value2) {
            addCriterion("testing_date between", value1, value2, "testingDate");
            return (Criteria) this;
        }

        public Criteria andTestingDateNotBetween(Date value1, Date value2) {
            addCriterion("testing_date not between", value1, value2, "testingDate");
            return (Criteria) this;
        }

        public Criteria andTestingMethodIsNull() {
            addCriterion("testing_method is null");
            return (Criteria) this;
        }

        public Criteria andTestingMethodIsNotNull() {
            addCriterion("testing_method is not null");
            return (Criteria) this;
        }

        public Criteria andTestingMethodEqualTo(String value) {
            addCriterion("testing_method =", value, "testingMethod");
            return (Criteria) this;
        }

        public Criteria andTestingMethodNotEqualTo(String value) {
            addCriterion("testing_method <>", value, "testingMethod");
            return (Criteria) this;
        }

        public Criteria andTestingMethodGreaterThan(String value) {
            addCriterion("testing_method >", value, "testingMethod");
            return (Criteria) this;
        }

        public Criteria andTestingMethodGreaterThanOrEqualTo(String value) {
            addCriterion("testing_method >=", value, "testingMethod");
            return (Criteria) this;
        }

        public Criteria andTestingMethodLessThan(String value) {
            addCriterion("testing_method <", value, "testingMethod");
            return (Criteria) this;
        }

        public Criteria andTestingMethodLessThanOrEqualTo(String value) {
            addCriterion("testing_method <=", value, "testingMethod");
            return (Criteria) this;
        }

        public Criteria andTestingMethodLike(String value) {
            addCriterion("testing_method like", value, "testingMethod");
            return (Criteria) this;
        }

        public Criteria andTestingMethodNotLike(String value) {
            addCriterion("testing_method not like", value, "testingMethod");
            return (Criteria) this;
        }

        public Criteria andTestingMethodIn(List<String> values) {
            addCriterion("testing_method in", values, "testingMethod");
            return (Criteria) this;
        }

        public Criteria andTestingMethodNotIn(List<String> values) {
            addCriterion("testing_method not in", values, "testingMethod");
            return (Criteria) this;
        }

        public Criteria andTestingMethodBetween(String value1, String value2) {
            addCriterion("testing_method between", value1, value2, "testingMethod");
            return (Criteria) this;
        }

        public Criteria andTestingMethodNotBetween(String value1, String value2) {
            addCriterion("testing_method not between", value1, value2, "testingMethod");
            return (Criteria) this;
        }

        public Criteria andTestingResultIsNull() {
            addCriterion("testing_result is null");
            return (Criteria) this;
        }

        public Criteria andTestingResultIsNotNull() {
            addCriterion("testing_result is not null");
            return (Criteria) this;
        }

        public Criteria andTestingResultEqualTo(String value) {
            addCriterion("testing_result =", value, "testingResult");
            return (Criteria) this;
        }

        public Criteria andTestingResultNotEqualTo(String value) {
            addCriterion("testing_result <>", value, "testingResult");
            return (Criteria) this;
        }

        public Criteria andTestingResultGreaterThan(String value) {
            addCriterion("testing_result >", value, "testingResult");
            return (Criteria) this;
        }

        public Criteria andTestingResultGreaterThanOrEqualTo(String value) {
            addCriterion("testing_result >=", value, "testingResult");
            return (Criteria) this;
        }

        public Criteria andTestingResultLessThan(String value) {
            addCriterion("testing_result <", value, "testingResult");
            return (Criteria) this;
        }

        public Criteria andTestingResultLessThanOrEqualTo(String value) {
            addCriterion("testing_result <=", value, "testingResult");
            return (Criteria) this;
        }

        public Criteria andTestingResultLike(String value) {
            addCriterion("testing_result like", value, "testingResult");
            return (Criteria) this;
        }

        public Criteria andTestingResultNotLike(String value) {
            addCriterion("testing_result not like", value, "testingResult");
            return (Criteria) this;
        }

        public Criteria andTestingResultIn(List<String> values) {
            addCriterion("testing_result in", values, "testingResult");
            return (Criteria) this;
        }

        public Criteria andTestingResultNotIn(List<String> values) {
            addCriterion("testing_result not in", values, "testingResult");
            return (Criteria) this;
        }

        public Criteria andTestingResultBetween(String value1, String value2) {
            addCriterion("testing_result between", value1, value2, "testingResult");
            return (Criteria) this;
        }

        public Criteria andTestingResultNotBetween(String value1, String value2) {
            addCriterion("testing_result not between", value1, value2, "testingResult");
            return (Criteria) this;
        }

        public Criteria andTestingPersonIsNull() {
            addCriterion("testing_person is null");
            return (Criteria) this;
        }

        public Criteria andTestingPersonIsNotNull() {
            addCriterion("testing_person is not null");
            return (Criteria) this;
        }

        public Criteria andTestingPersonEqualTo(String value) {
            addCriterion("testing_person =", value, "testingPerson");
            return (Criteria) this;
        }

        public Criteria andTestingPersonNotEqualTo(String value) {
            addCriterion("testing_person <>", value, "testingPerson");
            return (Criteria) this;
        }

        public Criteria andTestingPersonGreaterThan(String value) {
            addCriterion("testing_person >", value, "testingPerson");
            return (Criteria) this;
        }

        public Criteria andTestingPersonGreaterThanOrEqualTo(String value) {
            addCriterion("testing_person >=", value, "testingPerson");
            return (Criteria) this;
        }

        public Criteria andTestingPersonLessThan(String value) {
            addCriterion("testing_person <", value, "testingPerson");
            return (Criteria) this;
        }

        public Criteria andTestingPersonLessThanOrEqualTo(String value) {
            addCriterion("testing_person <=", value, "testingPerson");
            return (Criteria) this;
        }

        public Criteria andTestingPersonLike(String value) {
            addCriterion("testing_person like", value, "testingPerson");
            return (Criteria) this;
        }

        public Criteria andTestingPersonNotLike(String value) {
            addCriterion("testing_person not like", value, "testingPerson");
            return (Criteria) this;
        }

        public Criteria andTestingPersonIn(List<String> values) {
            addCriterion("testing_person in", values, "testingPerson");
            return (Criteria) this;
        }

        public Criteria andTestingPersonNotIn(List<String> values) {
            addCriterion("testing_person not in", values, "testingPerson");
            return (Criteria) this;
        }

        public Criteria andTestingPersonBetween(String value1, String value2) {
            addCriterion("testing_person between", value1, value2, "testingPerson");
            return (Criteria) this;
        }

        public Criteria andTestingPersonNotBetween(String value1, String value2) {
            addCriterion("testing_person not between", value1, value2, "testingPerson");
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