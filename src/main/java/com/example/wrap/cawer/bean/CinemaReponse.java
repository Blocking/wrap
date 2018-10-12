package com.example.wrap.cawer.bean;

/**
 * Create by zhangxy on 2018/10/10 15:06
 */
public class CinemaReponse {

    /**
     * status : success
     * data : {"id":9575,"code":"11051151","shortName":"北京市朝阳区明星时代影城梦秀店","cinemaChainId":12,"cinemaChainName":"北京明星时代数字电影院线有限公司","region":{"id":1,"code":"100000","name":"华北","actived":true,"orderNum":1},"province":{"id":1,"regionCode":"100000","regionName":"华北","code":"110000","name":"北京市","actived":true,"orderNum":1},"city":{"id":14,"provinceCode":"110000","provinceName":"北京市","regionName":"华北","code":"110500","name":"朝阳区","type":"firstTier","actived":true,"orderNum":3586},"county":{"id":3023,"cityCode":"110500","cityName":"朝阳区","provinceName":"北京市","regionName":"华北","code":"110500","name":"市辖区","actived":true,"orderNum":3618},"officalName":"北京明星时代影院管理有限公司朝阳分公司","address":"北京市朝阳区市辖区北京市朝阳区望京西路41号楼1至7层101内五层5-05号、六层6-01号","grade":"one","cinemaBasicInfo":{"id":null,"businessDate":1485187200000,"registrationDate":1485014400000,"cinemaLevel":"city","playUnitType":"city2","officalName":"北京明星时代影院管理有限公司朝阳分公司","zipCode":"100102","address":"北京市朝阳区市辖区北京市朝阳区望京西路41号楼1至7层101内五层5-05号、六层6-01号","sales":"周玲","salesTel":"18310331103","legalPerson":"杨木","legalPersonTel":"13708008111","manager":"王鑫","managerTel":"13811857654"},"applicant":"王鑫","applicantTel":"13811857654"}
     */

    private String status;
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 9575
         * code : 11051151
         * shortName : 北京市朝阳区明星时代影城梦秀店
         * cinemaChainId : 12
         * cinemaChainName : 北京明星时代数字电影院线有限公司
         * region : {"id":1,"code":"100000","name":"华北","actived":true,"orderNum":1}
         * province : {"id":1,"regionCode":"100000","regionName":"华北","code":"110000","name":"北京市","actived":true,"orderNum":1}
         * city : {"id":14,"provinceCode":"110000","provinceName":"北京市","regionName":"华北","code":"110500","name":"朝阳区","type":"firstTier","actived":true,"orderNum":3586}
         * county : {"id":3023,"cityCode":"110500","cityName":"朝阳区","provinceName":"北京市","regionName":"华北","code":"110500","name":"市辖区","actived":true,"orderNum":3618}
         * officalName : 北京明星时代影院管理有限公司朝阳分公司
         * address : 北京市朝阳区市辖区北京市朝阳区望京西路41号楼1至7层101内五层5-05号、六层6-01号
         * grade : one
         * cinemaBasicInfo : {"id":null,"businessDate":1485187200000,"registrationDate":1485014400000,"cinemaLevel":"city","playUnitType":"city2","officalName":"北京明星时代影院管理有限公司朝阳分公司","zipCode":"100102","address":"北京市朝阳区市辖区北京市朝阳区望京西路41号楼1至7层101内五层5-05号、六层6-01号","sales":"周玲","salesTel":"18310331103","legalPerson":"杨木","legalPersonTel":"13708008111","manager":"王鑫","managerTel":"13811857654"}
         * applicant : 王鑫
         * applicantTel : 13811857654
         */

        private int id;
        private String code;
        private String shortName;
        private int cinemaChainId;
        private String cinemaChainName;
        private RegionBean region;
        private ProvinceBean province;
        private CityBean city;
        private CountyBean county;
        private String officalName;
        private String address;
        private String grade;
        private CinemaBasicInfoBean cinemaBasicInfo;
        private String applicant;
        private String applicantTel;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getShortName() {
            return shortName;
        }

        public void setShortName(String shortName) {
            this.shortName = shortName;
        }

        public int getCinemaChainId() {
            return cinemaChainId;
        }

        public void setCinemaChainId(int cinemaChainId) {
            this.cinemaChainId = cinemaChainId;
        }

        public String getCinemaChainName() {
            return cinemaChainName;
        }

        public void setCinemaChainName(String cinemaChainName) {
            this.cinemaChainName = cinemaChainName;
        }

        public RegionBean getRegion() {
            return region;
        }

        public void setRegion(RegionBean region) {
            this.region = region;
        }

        public ProvinceBean getProvince() {
            return province;
        }

        public void setProvince(ProvinceBean province) {
            this.province = province;
        }

        public CityBean getCity() {
            return city;
        }

        public void setCity(CityBean city) {
            this.city = city;
        }

        public CountyBean getCounty() {
            return county;
        }

        public void setCounty(CountyBean county) {
            this.county = county;
        }

        public String getOfficalName() {
            return officalName;
        }

        public void setOfficalName(String officalName) {
            this.officalName = officalName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public CinemaBasicInfoBean getCinemaBasicInfo() {
            return cinemaBasicInfo;
        }

        public void setCinemaBasicInfo(CinemaBasicInfoBean cinemaBasicInfo) {
            this.cinemaBasicInfo = cinemaBasicInfo;
        }

        public String getApplicant() {
            return applicant;
        }

        public void setApplicant(String applicant) {
            this.applicant = applicant;
        }

        public String getApplicantTel() {
            return applicantTel;
        }

        public void setApplicantTel(String applicantTel) {
            this.applicantTel = applicantTel;
        }

        public static class RegionBean {
            /**
             * id : 1
             * code : 100000
             * name : 华北
             * actived : true
             * orderNum : 1
             */

            private int id;
            private String code;
            private String name;
            private boolean actived;
            private int orderNum;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public boolean isActived() {
                return actived;
            }

            public void setActived(boolean actived) {
                this.actived = actived;
            }

            public int getOrderNum() {
                return orderNum;
            }

            public void setOrderNum(int orderNum) {
                this.orderNum = orderNum;
            }
        }

        public static class ProvinceBean {
            /**
             * id : 1
             * regionCode : 100000
             * regionName : 华北
             * code : 110000
             * name : 北京市
             * actived : true
             * orderNum : 1
             */

            private int id;
            private String regionCode;
            private String regionName;
            private String code;
            private String name;
            private boolean actived;
            private int orderNum;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getRegionCode() {
                return regionCode;
            }

            public void setRegionCode(String regionCode) {
                this.regionCode = regionCode;
            }

            public String getRegionName() {
                return regionName;
            }

            public void setRegionName(String regionName) {
                this.regionName = regionName;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public boolean isActived() {
                return actived;
            }

            public void setActived(boolean actived) {
                this.actived = actived;
            }

            public int getOrderNum() {
                return orderNum;
            }

            public void setOrderNum(int orderNum) {
                this.orderNum = orderNum;
            }
        }

        public static class CityBean {
            /**
             * id : 14
             * provinceCode : 110000
             * provinceName : 北京市
             * regionName : 华北
             * code : 110500
             * name : 朝阳区
             * type : firstTier
             * actived : true
             * orderNum : 3586
             */

            private int id;
            private String provinceCode;
            private String provinceName;
            private String regionName;
            private String code;
            private String name;
            private String type;
            private boolean actived;
            private int orderNum;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getProvinceCode() {
                return provinceCode;
            }

            public void setProvinceCode(String provinceCode) {
                this.provinceCode = provinceCode;
            }

            public String getProvinceName() {
                return provinceName;
            }

            public void setProvinceName(String provinceName) {
                this.provinceName = provinceName;
            }

            public String getRegionName() {
                return regionName;
            }

            public void setRegionName(String regionName) {
                this.regionName = regionName;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public boolean isActived() {
                return actived;
            }

            public void setActived(boolean actived) {
                this.actived = actived;
            }

            public int getOrderNum() {
                return orderNum;
            }

            public void setOrderNum(int orderNum) {
                this.orderNum = orderNum;
            }
        }

        public static class CountyBean {
            /**
             * id : 3023
             * cityCode : 110500
             * cityName : 朝阳区
             * provinceName : 北京市
             * regionName : 华北
             * code : 110500
             * name : 市辖区
             * actived : true
             * orderNum : 3618
             */

            private int id;
            private String cityCode;
            private String cityName;
            private String provinceName;
            private String regionName;
            private String code;
            private String name;
            private boolean actived;
            private int orderNum;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCityCode() {
                return cityCode;
            }

            public void setCityCode(String cityCode) {
                this.cityCode = cityCode;
            }

            public String getCityName() {
                return cityName;
            }

            public void setCityName(String cityName) {
                this.cityName = cityName;
            }

            public String getProvinceName() {
                return provinceName;
            }

            public void setProvinceName(String provinceName) {
                this.provinceName = provinceName;
            }

            public String getRegionName() {
                return regionName;
            }

            public void setRegionName(String regionName) {
                this.regionName = regionName;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public boolean isActived() {
                return actived;
            }

            public void setActived(boolean actived) {
                this.actived = actived;
            }

            public int getOrderNum() {
                return orderNum;
            }

            public void setOrderNum(int orderNum) {
                this.orderNum = orderNum;
            }
        }

        public static class CinemaBasicInfoBean {
            /**
             * id : null
             * businessDate : 1485187200000
             * registrationDate : 1485014400000
             * cinemaLevel : city
             * playUnitType : city2
             * officalName : 北京明星时代影院管理有限公司朝阳分公司
             * zipCode : 100102
             * address : 北京市朝阳区市辖区北京市朝阳区望京西路41号楼1至7层101内五层5-05号、六层6-01号
             * sales : 周玲
             * salesTel : 18310331103
             * legalPerson : 杨木
             * legalPersonTel : 13708008111
             * manager : 王鑫
             * managerTel : 13811857654
             */

            private Object id;
            private long businessDate;
            private long registrationDate;
            private String cinemaLevel;
            private String playUnitType;
            private String officalName;
            private String zipCode;
            private String address;
            private String sales;
            private String salesTel;
            private String legalPerson;
            private String legalPersonTel;
            private String manager;
            private String managerTel;

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
                this.id = id;
            }

            public long getBusinessDate() {
                return businessDate;
            }

            public void setBusinessDate(long businessDate) {
                this.businessDate = businessDate;
            }

            public long getRegistrationDate() {
                return registrationDate;
            }

            public void setRegistrationDate(long registrationDate) {
                this.registrationDate = registrationDate;
            }

            public String getCinemaLevel() {
                return cinemaLevel;
            }

            public void setCinemaLevel(String cinemaLevel) {
                this.cinemaLevel = cinemaLevel;
            }

            public String getPlayUnitType() {
                return playUnitType;
            }

            public void setPlayUnitType(String playUnitType) {
                this.playUnitType = playUnitType;
            }

            public String getOfficalName() {
                return officalName;
            }

            public void setOfficalName(String officalName) {
                this.officalName = officalName;
            }

            public String getZipCode() {
                return zipCode;
            }

            public void setZipCode(String zipCode) {
                this.zipCode = zipCode;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getSales() {
                return sales;
            }

            public void setSales(String sales) {
                this.sales = sales;
            }

            public String getSalesTel() {
                return salesTel;
            }

            public void setSalesTel(String salesTel) {
                this.salesTel = salesTel;
            }

            public String getLegalPerson() {
                return legalPerson;
            }

            public void setLegalPerson(String legalPerson) {
                this.legalPerson = legalPerson;
            }

            public String getLegalPersonTel() {
                return legalPersonTel;
            }

            public void setLegalPersonTel(String legalPersonTel) {
                this.legalPersonTel = legalPersonTel;
            }

            public String getManager() {
                return manager;
            }

            public void setManager(String manager) {
                this.manager = manager;
            }

            public String getManagerTel() {
                return managerTel;
            }

            public void setManagerTel(String managerTel) {
                this.managerTel = managerTel;
            }
        }
    }
}
