package placeme.octopusites.com.placeme;

/**
 * Created by sunny1 on 22/06/2017.
 */

public class AdminData {
    static String fname,mname="",lname,country,state,city,phone="",institute;
    static String  instemail,instweb,instphone,instaltrphone,univname,instregno;
    static String lang1,proficiency1,lang2,proficiency2,lang3,proficiency3,lang4,proficiency4,lang5,proficiency5,lang6,proficiency6,lang7,proficiency7,lang8,proficiency8,lang9,proficiency9,lang10,proficiency10;
    static String skill1,skill2,skill3,skill4,skill5,skill6,skill7,skill8,skill9,skill10,skill11,skill12,skill13,skill14,skill15,skill16,skill17,skill18,skill19,skill20,sproficiency1,sproficiency2,sproficiency3,sproficiency4,sproficiency5,sproficiency6,sproficiency7,sproficiency8,sproficiency9,sproficiency10,sproficiency11,sproficiency12,sproficiency13,sproficiency14,sproficiency15,sproficiency16,sproficiency17,sproficiency18,sproficiency19,sproficiency20;
    static String htitle1,hissuer1,hdescription1,htitle2,hissuer2,hdescription2,htitle3,hissuer3,hdescription3,htitle4,hissuer4,hdescription4,htitle5,hissuer5,hdescription5,htitle6,hissuer6,hdescription6,htitle7,hissuer7,hdescription7,htitle8,hissuer8,hdescription8,htitle9,hissuer9,hdescription9,htitle10,hissuer10,hdescription10,yearofhonor1,yearofhonor2,yearofhonor3,yearofhonor4,yearofhonor5,yearofhonor6,yearofhonor7,yearofhonor8,yearofhonor9,yearofhonor10;
    static String ptitle1,pappno1,pinventor1,pissue1,pfiling1,purl1,pdescription1,ptitle2,pappno2,pinventor2,pissue2,pfiling2,purl2,pdescription2,ptitle3,pappno3,pinventor3,pissue3,pfiling3,purl3,pdescription3,ptitle4,pappno4,pinventor4,pissue4,pfiling4,purl4,pdescription4,ptitle5,pappno5,pinventor5,pissue5,pfiling5,purl5,pdescription5,ptitle6,pappno6,pinventor6,pissue6,pfiling6,purl6,pdescription6,ptitle7,pappno7,pinventor7,pissue7,pfiling7,purl7,pdescription7,ptitle8,pappno8,pinventor8,pissue8,pfiling8,purl8,pdescription8,ptitle9,pappno9,pinventor9,pissue9,pfiling9,purl9,pdescription9,ptitle10,pappno10,pinventor10,pissue10,pfiling10,purl10,pdescription10,pselectedcountry1,pselectedcountry2,pselectedcountry3,pselectedcountry4,pselectedcountry5,pselectedcountry6,pselectedcountry7,pselectedcountry8,pselectedcountry9,pselectedcountry10,issuedorpending1,issuedorpending2,issuedorpending3,issuedorpending4,issuedorpending5,issuedorpending6,issuedorpending7,issuedorpending8,issuedorpending9,issuedorpending10;
    static String pubtitle1,publication1,author1,puburl1,pubdescription1,pubtitle2,publication2,author2,puburl2,pubdescription2,pubtitle3,publication3,author3,puburl3,pubdescription3,pubtitle4,publication4,author4,puburl4,pubdescription4,pubtitle5,publication5,author5,puburl5,pubdescription5,pubtitle6,publication6,author6,puburl6,pubdescription6,pubtitle7,publication7,author7,puburl7,pubdescription7,pubtitle8,publication8,author8,puburl8,pubdescription8,pubtitle9,publication9,author9,puburl9,pubdescription9,pubtitle10,publication10,author10,puburl10,pubdescription10,publicationdate1,publicationdate2,publicationdate3,publicationdate4,publicationdate5,publicationdate6,publicationdate7,publicationdate8,publicationdate9,publicationdate10;
    static String email2,addressline1,addressline2,addressline3,telephone,mobile2;
    static   String  post1,inst1,post2,inst2,post3,inst3,post4,inst4,post5,inst5,post6,inst6,post7,inst7,post8,inst8,post9,inst9,post10,inst10;
    static String  fromdate1,todate1,fromdate2,todate2,fromdate3,todate3,fromdate4,todate4,fromdate5,todate5,fromdate6,todate6,fromdate7,todate7,fromdate8,todate8,fromdate9,todate9,fromdate10,todate10;

    public static String getInstcaddrline3() {
        return instcaddrline3;
    }

    public static void setInstcaddrline3(String instcaddrline3) {
        AdminData.instcaddrline3 = instcaddrline3;
    }

    public static String getInstcaddrline1() {
        return instcaddrline1;
    }

    public static void setInstcaddrline1(String instcaddrline1) {
        AdminData.instcaddrline1 = instcaddrline1;
    }

    public static String getInstcaddrline2() {
        return instcaddrline2;
    }

    public static void setInstcaddrline2(String instcaddrline2) {
        AdminData.instcaddrline2 = instcaddrline2;
    }

    static String  instcaddrline1, instcaddrline2, instcaddrline3;





    void setFname(String fname){ this.fname=fname; }
    String getFname()
    {
        return fname;
    }
    void setMname(String mname){
        this.mname=mname;
    }
    String getMname()
    {
        return mname;
    }
    void setLname(String lname){
        this.lname=lname;
    }
    String getLname()
    {
        return lname;
    }
    void setCountry(String country){
        this.country=country;
    }
    String getCountry()
    {
        return country;
    }
    void setState(String state){
        this.state=state;
    }
    String getState()
    {
        return state;
    }
    void setCity(String city){
        this.city=city;
    }
    String getCity()
    {
        return city;
    }
    void setPhone(String phone){
        this.phone=phone;
    }
    String getPhone()
    {
        return phone;
    }
    void setInstitute(String institute) {
        this.institute=institute;
    }
    String getInstitute()
    {
        return institute;
    }

    void setInstweb(String instweb) {
        this.instweb = instweb;
    }
    String getInstweb()
    {
        return instweb;
    }

    void setInstemail(String instemail) {
        this.instemail = instemail;
    }
    String getInstemail()
    {
        return instemail;
    }

    void setInstphone(String instphone) {
        this.instphone = instphone;
    }
    String getInstphone()
    {
        return instphone;
    }

    void setInstaltrphone(String instaltrphone) {
        this.instaltrphone = instaltrphone;
    }
    String getInstaltrphone()
    {
        return instaltrphone;
    }
    void setUnivname(String univname) {
        this.univname = univname;
    }
    String getUnivname()
    {
        return univname;
    }
    void setInstregno(String instregno) {
        this.instregno = instregno;
    }
    String getInstregno()
    {
        return instregno;
    }

    void setLang1(String lang1){ this.lang1=lang1;}
    String getLang1()
    {
        return lang1;
    }
    void setProficiency1(String proficiency1){ this.proficiency1=proficiency1;}
    String getProficiency1()
    {
        return proficiency1;
    }
    void setLang2(String lang2){ this.lang2=lang2;}
    String getLang2()
    {
        return lang2;
    }
    void setProficiency2(String proficiency2){ this.proficiency2=proficiency2;}
    String getProficiency2()
    {
        return proficiency2;
    }
    void setLang3(String lang3){ this.lang3=lang3;}
    String getLang3()
    {
        return lang3;
    }
    void setProficiency3(String proficiency3){ this.proficiency3=proficiency3;}
    String getProficiency3()
    {
        return proficiency3;
    }
    void setLang4(String lang4){ this.lang4=lang4;}
    String getLang4()
    {
        return lang4;
    }
    void setProficiency4(String proficiency4){ this.proficiency4=proficiency4;}
    String getProficiency4()
    {
        return proficiency4;
    }
    void setLang5(String lang5){ this.lang5=lang5;}
    String getLang5()
    {
        return lang5;
    }
    void setProficiency5(String proficiency5){ this.proficiency5=proficiency5;}
    String getProficiency5()
    {
        return proficiency5;
    }
    void setLang6(String lang6){ this.lang6=lang6;}
    String getLang6()
    {
        return lang6;
    }
    void setProficiency6(String proficiency6){ this.proficiency6=proficiency6;}
    String getProficiency6()
    {
        return proficiency6;
    }
    void setLang7(String lang7){ this.lang7=lang7;}
    String getLang7()
    {
        return lang7;
    }
    void setProficiency7(String proficiency7){ this.proficiency7=proficiency7;}
    String getProficiency7()
    {
        return proficiency7;
    }
    void setLang8(String lang8){ this.lang8=lang8;}
    String getLang8()
    {
        return lang8;
    }
    void setProficiency8(String proficiency8){ this.proficiency8=proficiency8;}
    String getProficiency8()
    {
        return proficiency8;
    }
    void setLang9(String lang9){ this.lang9=lang9;}
    String getLang9()
    {
        return lang9;
    }
    void setProficiency9(String proficiency9){ this.proficiency9=proficiency9;}
    String getProficiency9()
    {
        return proficiency9;
    }
    void setLang10(String lang10){ this.lang10=lang10;}
    String getLang10()
    {
        return lang10;
    }
    void setProficiency10(String proficiency10){ this.proficiency10=proficiency10;}
    String getProficiency10()
    {
        return proficiency10;
    }

    //
    void setSkill1(String skill1){ this.skill1=skill1;}
    String getSkill1()
    {
        return skill1;
    }
    void setSproficiency1(String sproficiency1){ this.sproficiency1=sproficiency1;}
    String getSproficiency1()
    {
        return sproficiency1;
    }
    void setSkill2(String skill2){ this.skill2=skill2;}
    String getSkill2()
    {
        return skill2;
    }
    void setSproficiency2(String sproficiency2){ this.sproficiency2=sproficiency2;}
    String getSproficiency2()
    {
        return sproficiency2;
    }
    void setSkill3(String skill3){ this.skill3=skill3;}
    String getSkill3()
    {
        return skill3;
    }
    void setSproficiency3(String sproficiency3){ this.sproficiency3=sproficiency3;}
    String getSproficiency3()
    {
        return sproficiency3;
    }
    void setSkill4(String skill4){ this.skill4=skill4;}
    String getSkill4()
    {
        return skill4;
    }
    void setSproficiency4(String sproficiency4){ this.sproficiency4=sproficiency4;}
    String getSproficiency4()
    {
        return sproficiency4;
    }
    void setSkill5(String skill5){ this.skill5=skill5;}
    String getSkill5()
    {
        return skill5;
    }
    void setSproficiency5(String sproficiency5){ this.sproficiency5=sproficiency5;}
    String getSproficiency5()
    {
        return sproficiency5;
    }
    void setSkill6(String skill6){ this.skill6=skill6;}
    String getSkill6()
    {
        return skill6;
    }
    void setSproficiency6(String sproficiency6){ this.sproficiency6=sproficiency6;}
    String getSproficiency6()
    {
        return sproficiency6;
    }
    void setSkill7(String skill7){ this.skill7=skill7;}
    String getSkill7()
    {
        return skill7;
    }
    void setSproficiency7(String sproficiency7){ this.sproficiency7=sproficiency7;}
    String getSproficiency7()
    {
        return sproficiency7;
    }
    void setSkill8(String skill8){ this.skill8=skill8;}
    String getSkill8()
    {
        return skill8;
    }
    void setSproficiency8(String sproficiency8){ this.sproficiency8=sproficiency8;}
    String getSproficiency8()
    {
        return sproficiency8;
    }
    void setSkill9(String skill9){ this.skill9=skill9;}
    String getSkill9()
    {
        return skill9;
    }
    void setSproficiency9(String sproficiency9){ this.sproficiency9=sproficiency9;}
    String getSproficiency9()
    {
        return sproficiency9;
    }
    void setSkill10(String skill10){ this.skill10=skill10;}
    String getSkill10()
    {
        return skill10;
    }
    void setSproficiency10(String sproficiency10){ this.sproficiency10=sproficiency10;}
    String getSproficiency10()
    {
        return sproficiency10;
    }
    void setSkill11(String skill11){ this.skill11=skill11;}
    String getSkill11()
    {
        return skill11;
    }
    void setSproficiency11(String sproficiency11){ this.sproficiency11=sproficiency11;}
    String getSproficiency11()
    {
        return sproficiency11;
    }
    void setSkill12(String skill12){ this.skill12=skill12;}
    String getSkill12()
    {
        return skill12;
    }
    void setSproficiency12(String sproficiency12){ this.sproficiency12=sproficiency12;}
    String getSproficiency12()
    {
        return sproficiency12;
    }
    void setSkill13(String skill13){ this.skill13=skill13;}
    String getSkill13()
    {
        return skill13;
    }
    void setSproficiency13(String sproficiency13){ this.sproficiency13=sproficiency13;}
    String getSproficiency13()
    {
        return sproficiency13;
    }
    void setSkill14(String skill14){ this.skill14=skill14;}
    String getSkill14()
    {
        return skill14;
    }
    void setSproficiency14(String sproficiency14){ this.sproficiency14=sproficiency14;}
    String getSproficiency14()
    {
        return sproficiency14;
    }
    void setSkill15(String skill15){ this.skill15=skill15;}
    String getSkill15()
    {
        return skill15;
    }
    void setSproficiency15(String sproficiency15){ this.sproficiency15=sproficiency15;}
    String getSproficiency15()
    {
        return sproficiency15;
    }
    void setSkill16(String skill16){ this.skill16=skill16;}
    String getSkill16()
    {
        return skill16;
    }
    void setSproficiency16(String sproficiency16){ this.sproficiency16=sproficiency16;}
    String getSproficiency16()
    {
        return sproficiency16;
    }
    void setSkill17(String skill17){ this.skill17=skill17;}
    String getSkill17()
    {
        return skill17;
    }
    void setSproficiency17(String sproficiency17){ this.sproficiency17=sproficiency17;}
    String getSproficiency17()
    {
        return sproficiency17;
    }
    void setSkill18(String skill18){ this.skill18=skill18;}
    String getSkill18()
    {
        return skill18;
    }
    void setSproficiency18(String sproficiency18){ this.sproficiency18=sproficiency18;}
    String getSproficiency18()
    {
        return sproficiency18;
    }
    void setSkill19(String skill19){ this.skill19=skill19;}
    String getSkill19()
    {
        return skill19;
    }
    void setSproficiency19(String sproficiency19){ this.sproficiency19=sproficiency19;}
    String getSproficiency19()
    {
        return sproficiency19;
    }
    void setSkill20(String skill20){ this.skill20=skill20;}
    String getSkill20()
    {
        return skill20;
    }
    void setSproficiency20(String sproficiency20){ this.sproficiency20=sproficiency20;}
    String getSproficiency20(){return sproficiency20;}
//

    void setHtitle1(String htitle1){ this.htitle1=htitle1;}
    String getHtitle1(){return htitle1;}
    void setHissuer1(String hissuer1){ this.hissuer1=hissuer1;}
    String getHissuer1(){return hissuer1;}
    void setHdescription1(String hdescription1){ this.hdescription1=hdescription1;}
    String getHdescription1(){return hdescription1;}
    void setYearofhonor1(String yearofhonor1){ this.yearofhonor1=yearofhonor1;}
    String getYearofhonor1(){return yearofhonor1;}
    void setHtitle2(String htitle2){ this.htitle2=htitle2;}
    String getHtitle2(){return htitle2;}
    void setHissuer2(String hissuer2){ this.hissuer2=hissuer2;}
    String getHissuer2(){return hissuer2;}
    void setHdescription2(String hdescription2){ this.hdescription2=hdescription2;}
    String getHdescription2(){return hdescription2;}
    void setYearofhonor2(String yearofhonor2){ this.yearofhonor2=yearofhonor2;}
    String getYearofhonor2(){return yearofhonor2;}
    void setHtitle3(String htitle3){ this.htitle3=htitle3;}
    String getHtitle3(){return htitle3;}
    void setHissuer3(String hissuer3){ this.hissuer3=hissuer3;}
    String getHissuer3(){return hissuer3;}
    void setHdescription3(String hdescription3){ this.hdescription3=hdescription3;}
    String getHdescription3(){return hdescription3;}
    void setYearofhonor3(String yearofhonor3){ this.yearofhonor3=yearofhonor3;}
    String getYearofhonor3(){return yearofhonor3;}
    void setHtitle4(String htitle4){ this.htitle4=htitle4;}
    String getHtitle4(){return htitle4;}
    void setHissuer4(String hissuer4){ this.hissuer4=hissuer4;}
    String getHissuer4(){return hissuer4;}
    void setHdescription4(String hdescription4){ this.hdescription4=hdescription4;}
    String getHdescription4(){return hdescription4;}
    void setYearofhonor4(String yearofhonor4){ this.yearofhonor4=yearofhonor4;}
    String getYearofhonor4(){return yearofhonor4;}
    void setHtitle5(String htitle5){ this.htitle5=htitle5;}
    String getHtitle5(){return htitle5;}
    void setHissuer5(String hissuer5){ this.hissuer5=hissuer5;}
    String getHissuer5(){return hissuer5;}
    void setHdescription5(String hdescription5){ this.hdescription5=hdescription5;}
    String getHdescription5(){return hdescription5;}
    void setYearofhonor5(String yearofhonor5){ this.yearofhonor5=yearofhonor5;}
    String getYearofhonor5(){return yearofhonor5;}
    void setHtitle6(String htitle6){ this.htitle6=htitle6;}
    String getHtitle6(){return htitle6;}
    void setHissuer6(String hissuer6){ this.hissuer6=hissuer6;}
    String getHissuer6(){return hissuer6;}
    void setHdescription6(String hdescription6){ this.hdescription6=hdescription6;}
    String getHdescription6(){return hdescription6;}
    void setYearofhonor6(String yearofhonor6){ this.yearofhonor6=yearofhonor6;}
    String getYearofhonor6(){return yearofhonor6;}
    void setHtitle7(String htitle7){ this.htitle7=htitle7;}
    String getHtitle7(){return htitle7;}
    void setHissuer7(String hissuer7){ this.hissuer7=hissuer7;}
    String getHissuer7(){return hissuer7;}
    void setHdescription7(String hdescription7){ this.hdescription7=hdescription7;}
    String getHdescription7(){return hdescription7;}
    void setYearofhonor7(String yearofhonor7){ this.yearofhonor7=yearofhonor7;}
    String getYearofhonor7(){return yearofhonor7;}
    void setHtitle8(String htitle8){ this.htitle8=htitle8;}
    String getHtitle8(){return htitle8;}
    void setHissuer8(String hissuer8){ this.hissuer8=hissuer8;}
    String getHissuer8(){return hissuer8;}
    void setHdescription8(String hdescription8){ this.hdescription8=hdescription8;}
    String getHdescription8(){return hdescription8;}
    void setYearofhonor8(String yearofhonor8){ this.yearofhonor8=yearofhonor8;}
    String getYearofhonor8(){return yearofhonor8;}
    void setHtitle9(String htitle9){ this.htitle9=htitle9;}
    String getHtitle9(){return htitle9;}
    void setHissuer9(String hissuer9){ this.hissuer9=hissuer9;}
    String getHissuer9(){return hissuer9;}
    void setHdescription9(String hdescription9){ this.hdescription9=hdescription9;}
    String getHdescription9(){return hdescription9;}
    void setYearofhonor9(String yearofhonor9){ this.yearofhonor9=yearofhonor9;}
    String getYearofhonor9(){return yearofhonor9;}
    void setHtitle10(String htitle10){ this.htitle10=htitle10;}
    String getHtitle10(){return htitle10;}
    void setHissuer10(String hissuer10){ this.hissuer10=hissuer10;}
    String getHissuer10(){return hissuer10;}
    void setHdescription10(String hdescription10){ this.hdescription10=hdescription10;}
    String getHdescription10(){return hdescription10;}
    void setYearofhonor10(String yearofhonor10){ this.yearofhonor10=yearofhonor10;}
    String getYearofhonor10(){return yearofhonor10;}
    //
    void setPtitle1(String ptitle1){ this.ptitle1=ptitle1;}
    String getPtitle1(){return ptitle1;}
    void setPappno1(String pappno1){ this.pappno1=pappno1;}
    String getPappno1(){return pappno1;}
    void setPinventor1(String pinventor1){ this.pinventor1=pinventor1;}
    String getPinventor1(){return pinventor1;}
    void setPissue1(String pissue1){ this.pissue1=pissue1;}
    String getPissue1(){return pissue1;}
    void setPfiling1(String pfiling1){ this.pfiling1=pfiling1;}
    String getPfiling1(){return pfiling1;}
    void setPurl1(String purl1){ this.purl1=purl1;}
    String getPurl1(){return purl1;}
    void setPdescription1(String pdescription1){ this.pdescription1=pdescription1;}
    String getPdescription1(){return pdescription1;}
    void setPselectedcountry1(String pselectedcountry1){ this.pselectedcountry1=pselectedcountry1;}
    String getPselectedcountry1(){return pselectedcountry1;}
    void setIssuedorpending1(String issuedorpending1){ this.issuedorpending1=issuedorpending1;}
    String getIssuedorpending1(){return issuedorpending1;}
    void setPtitle2(String ptitle2){ this.ptitle2=ptitle2;}
    String getPtitle2(){return ptitle2;}
    void setPappno2(String pappno2){ this.pappno2=pappno2;}
    String getPappno2(){return pappno2;}
    void setPinventor2(String pinventor2){ this.pinventor2=pinventor2;}
    String getPinventor2(){return pinventor2;}
    void setPissue2(String pissue2){ this.pissue2=pissue2;}
    String getPissue2(){return pissue2;}
    void setPfiling2(String pfiling2){ this.pfiling2=pfiling2;}
    String getPfiling2(){return pfiling2;}
    void setPurl2(String purl2){ this.purl2=purl2;}
    String getPurl2(){return purl2;}
    void setPdescription2(String pdescription2){ this.pdescription2=pdescription2;}
    String getPdescription2(){return pdescription2;}
    void setPselectedcountry2(String pselectedcountry2){ this.pselectedcountry2=pselectedcountry2;}
    String getPselectedcountry2(){return pselectedcountry2;}
    void setIssuedorpending2(String issuedorpending2){ this.issuedorpending2=issuedorpending2;}
    String getIssuedorpending2(){return issuedorpending2;}
    void setPtitle3(String ptitle3){ this.ptitle3=ptitle3;}
    String getPtitle3(){return ptitle3;}
    void setPappno3(String pappno3){ this.pappno3=pappno3;}
    String getPappno3(){return pappno3;}
    void setPinventor3(String pinventor3){ this.pinventor3=pinventor3;}
    String getPinventor3(){return pinventor3;}
    void setPissue3(String pissue3){ this.pissue3=pissue3;}
    String getPissue3(){return pissue3;}
    void setPfiling3(String pfiling3){ this.pfiling3=pfiling3;}
    String getPfiling3(){return pfiling3;}
    void setPurl3(String purl3){ this.purl3=purl3;}
    String getPurl3(){return purl3;}
    void setPdescription3(String pdescription3){ this.pdescription3=pdescription3;}
    String getPdescription3(){return pdescription3;}
    void setPselectedcountry3(String pselectedcountry3){ this.pselectedcountry3=pselectedcountry3;}
    String getPselectedcountry3(){return pselectedcountry3;}
    void setIssuedorpending3(String issuedorpending3){ this.issuedorpending3=issuedorpending3;}
    String getIssuedorpending3(){return issuedorpending3;}
    void setPtitle4(String ptitle4){ this.ptitle4=ptitle4;}
    String getPtitle4(){return ptitle4;}
    void setPappno4(String pappno4){ this.pappno4=pappno4;}
    String getPappno4(){return pappno4;}
    void setPinventor4(String pinventor4){ this.pinventor4=pinventor4;}
    String getPinventor4(){return pinventor4;}
    void setPissue4(String pissue4){ this.pissue4=pissue4;}
    String getPissue4(){return pissue4;}
    void setPfiling4(String pfiling4){ this.pfiling4=pfiling4;}
    String getPfiling4(){return pfiling4;}
    void setPurl4(String purl4){ this.purl4=purl4;}
    String getPurl4(){return purl4;}
    void setPdescription4(String pdescription4){ this.pdescription4=pdescription4;}
    String getPdescription4(){return pdescription4;}
    void setPselectedcountry4(String pselectedcountry4){ this.pselectedcountry4=pselectedcountry4;}
    String getPselectedcountry4(){return pselectedcountry4;}
    void setIssuedorpending4(String issuedorpending4){ this.issuedorpending4=issuedorpending4;}
    String getIssuedorpending4(){return issuedorpending4;}
    void setPtitle5(String ptitle5){ this.ptitle5=ptitle5;}
    String getPtitle5(){return ptitle5;}
    void setPappno5(String pappno5){ this.pappno5=pappno5;}
    String getPappno5(){return pappno5;}
    void setPinventor5(String pinventor5){ this.pinventor5=pinventor5;}
    String getPinventor5(){return pinventor5;}
    void setPissue5(String pissue5){ this.pissue5=pissue5;}
    String getPissue5(){return pissue5;}
    void setPfiling5(String pfiling5){ this.pfiling5=pfiling5;}
    String getPfiling5(){return pfiling5;}
    void setPurl5(String purl5){ this.purl5=purl5;}
    String getPurl5(){return purl5;}
    void setPdescription5(String pdescription5){ this.pdescription5=pdescription5;}
    String getPdescription5(){return pdescription5;}
    void setPselectedcountry5(String pselectedcountry5){ this.pselectedcountry5=pselectedcountry5;}
    String getPselectedcountry5(){return pselectedcountry5;}
    void setIssuedorpending5(String issuedorpending5){ this.issuedorpending5=issuedorpending5;}
    String getIssuedorpending5(){return issuedorpending5;}
    void setPtitle6(String ptitle6){ this.ptitle6=ptitle6;}
    String getPtitle6(){return ptitle6;}
    void setPappno6(String pappno6){ this.pappno6=pappno6;}
    String getPappno6(){return pappno6;}
    void setPinventor6(String pinventor6){ this.pinventor6=pinventor6;}
    String getPinventor6(){return pinventor6;}
    void setPissue6(String pissue6){ this.pissue6=pissue6;}
    String getPissue6(){return pissue6;}
    void setPfiling6(String pfiling6){ this.pfiling6=pfiling6;}
    String getPfiling6(){return pfiling6;}
    void setPurl6(String purl6){ this.purl6=purl6;}
    String getPurl6(){return purl6;}
    void setPdescription6(String pdescription6){ this.pdescription6=pdescription6;}
    String getPdescription6(){return pdescription6;}
    void setPselectedcountry6(String pselectedcountry6){ this.pselectedcountry6=pselectedcountry6;}
    String getPselectedcountry6(){return pselectedcountry6;}
    void setIssuedorpending6(String issuedorpending6){ this.issuedorpending6=issuedorpending6;}
    String getIssuedorpending6(){return issuedorpending6;}
    void setPtitle7(String ptitle7){ this.ptitle7=ptitle7;}
    String getPtitle7(){return ptitle7;}
    void setPappno7(String pappno7){ this.pappno7=pappno7;}
    String getPappno7(){return pappno7;}
    void setPinventor7(String pinventor7){ this.pinventor7=pinventor7;}
    String getPinventor7(){return pinventor7;}
    void setPissue7(String pissue7){ this.pissue7=pissue7;}
    String getPissue7(){return pissue7;}
    void setPfiling7(String pfiling7){ this.pfiling7=pfiling7;}
    String getPfiling7(){return pfiling7;}
    void setPurl7(String purl7){ this.purl7=purl7;}
    String getPurl7(){return purl7;}
    void setPdescription7(String pdescription7){ this.pdescription7=pdescription7;}
    String getPdescription7(){return pdescription7;}
    void setPselectedcountry7(String pselectedcountry7){ this.pselectedcountry7=pselectedcountry7;}
    String getPselectedcountry7(){return pselectedcountry7;}
    void setIssuedorpending7(String issuedorpending7){ this.issuedorpending7=issuedorpending7;}
    String getIssuedorpending7(){return issuedorpending7;}
    void setPtitle8(String ptitle8){ this.ptitle8=ptitle8;}
    String getPtitle8(){return ptitle8;}
    void setPappno8(String pappno8){ this.pappno8=pappno8;}
    String getPappno8(){return pappno8;}
    void setPinventor8(String pinventor8){ this.pinventor8=pinventor8;}
    String getPinventor8(){return pinventor8;}
    void setPissue8(String pissue8){ this.pissue8=pissue8;}
    String getPissue8(){return pissue8;}
    void setPfiling8(String pfiling8){ this.pfiling8=pfiling8;}
    String getPfiling8(){return pfiling8;}
    void setPurl8(String purl8){ this.purl8=purl8;}
    String getPurl8(){return purl8;}
    void setPdescription8(String pdescription8){ this.pdescription8=pdescription8;}
    String getPdescription8(){return pdescription8;}
    void setPselectedcountry8(String pselectedcountry8){ this.pselectedcountry8=pselectedcountry8;}
    String getPselectedcountry8(){return pselectedcountry8;}
    void setIssuedorpending8(String issuedorpending8){ this.issuedorpending8=issuedorpending8;}
    String getIssuedorpending8(){return issuedorpending8;}
    void setPtitle9(String ptitle9){ this.ptitle9=ptitle9;}
    String getPtitle9(){return ptitle9;}
    void setPappno9(String pappno9){ this.pappno9=pappno9;}
    String getPappno9(){return pappno9;}
    void setPinventor9(String pinventor9){ this.pinventor9=pinventor9;}
    String getPinventor9(){return pinventor9;}
    void setPissue9(String pissue9){ this.pissue9=pissue9;}
    String getPissue9(){return pissue9;}
    void setPfiling9(String pfiling9){ this.pfiling9=pfiling9;}
    String getPfiling9(){return pfiling9;}
    void setPurl9(String purl9){ this.purl9=purl9;}
    String getPurl9(){return purl9;}
    void setPdescription9(String pdescription9){ this.pdescription9=pdescription9;}
    String getPdescription9(){return pdescription9;}
    void setPselectedcountry9(String pselectedcountry9){ this.pselectedcountry9=pselectedcountry9;}
    String getPselectedcountry9(){return pselectedcountry9;}
    void setIssuedorpending9(String issuedorpending9){ this.issuedorpending9=issuedorpending9;}
    String getIssuedorpending9(){return issuedorpending9;}
    void setPtitle10(String ptitle10){ this.ptitle10=ptitle10;}
    String getPtitle10(){return ptitle10;}
    void setPappno10(String pappno10){ this.pappno10=pappno10;}
    String getPappno10(){return pappno10;}
    void setPinventor10(String pinventor10){ this.pinventor10=pinventor10;}
    String getPinventor10(){return pinventor10;}
    void setPissue10(String pissue10){ this.pissue10=pissue10;}
    String getPissue10(){return pissue10;}
    void setPfiling10(String pfiling10){ this.pfiling10=pfiling10;}
    String getPfiling10(){return pfiling10;}
    void setPurl10(String purl10){ this.purl10=purl10;}
    String getPurl10(){return purl10;}
    void setPdescription10(String pdescription10){ this.pdescription10=pdescription10;}
    String getPdescription10(){return pdescription10;}
    void setPselectedcountry10(String pselectedcountry10){ this.pselectedcountry10=pselectedcountry10;}
    String getPselectedcountry10(){return pselectedcountry10;}
    void setIssuedorpending10(String issuedorpending10){ this.issuedorpending10=issuedorpending10;}
    String getIssuedorpending10(){return issuedorpending10;}
    //
    void setPubtitle1(String pubtitle1){
        this.pubtitle1=pubtitle1;
    }
    String getPubtitle1()
    {
        return pubtitle1;
    }
    void setPublication1(String publication1){
        this.publication1=publication1;
    }
    String getPublication1()
    {
        return publication1;
    }
    void setAuthor1(String author1){
        this.author1=author1;
    }
    String getAuthor1()
    {
        return author1;
    }
    void setPuburl1(String puburl1){
        this.puburl1=puburl1;
    }
    String getPuburl1()
    {
        return puburl1;
    }
    void setPubdescription1(String pubdescription1){
        this.pubdescription1=pubdescription1;
    }
    String getPubdescription1()
    {
        return pubdescription1;
    }
    void setPubtitle2(String pubtitle2){
        this.pubtitle2=pubtitle2;
    }
    String getPubtitle2()
    {
        return pubtitle2;
    }
    void setPublication2(String publication2){
        this.publication2=publication2;
    }
    String getPublication2()
    {
        return publication2;
    }
    void setAuthor2(String author2){
        this.author2=author2;
    }
    String getAuthor2()
    {
        return author2;
    }
    void setPuburl2(String puburl2){
        this.puburl2=puburl2;
    }
    String getPuburl2()
    {
        return puburl2;
    }
    void setPubdescription2(String pubdescription2){
        this.pubdescription2=pubdescription2;
    }
    String getPubdescription2()
    {
        return pubdescription2;
    }
    void setPubtitle3(String pubtitle3){
        this.pubtitle3=pubtitle3;
    }
    String getPubtitle3()
    {
        return pubtitle3;
    }
    void setPublication3(String publication3){
        this.publication3=publication3;
    }
    String getPublication3()
    {
        return publication3;
    }
    void setAuthor3(String author3){
        this.author3=author3;
    }
    String getAuthor3()
    {
        return author3;
    }
    void setPuburl3(String puburl3){
        this.puburl3=puburl3;
    }
    String getPuburl3()
    {
        return puburl3;
    }
    void setPubdescription3(String pubdescription3){
        this.pubdescription3=pubdescription3;
    }
    String getPubdescription3()
    {
        return pubdescription3;
    }
    void setPubtitle4(String pubtitle4){
        this.pubtitle4=pubtitle4;
    }
    String getPubtitle4()
    {
        return pubtitle4;
    }
    void setPublication4(String publication4){
        this.publication4=publication4;
    }
    String getPublication4()
    {
        return publication4;
    }
    void setAuthor4(String author4){
        this.author4=author4;
    }
    String getAuthor4()
    {
        return author4;
    }
    void setPuburl4(String puburl4){
        this.puburl4=puburl4;
    }
    String getPuburl4()
    {
        return puburl4;
    }
    void setPubdescription4(String pubdescription4){
        this.pubdescription4=pubdescription4;
    }
    String getPubdescription4()
    {
        return pubdescription4;
    }
    void setPubtitle5(String pubtitle5){
        this.pubtitle5=pubtitle5;
    }
    String getPubtitle5()
    {
        return pubtitle5;
    }
    void setPublication5(String publication5){
        this.publication5=publication5;
    }
    String getPublication5()
    {
        return publication5;
    }
    void setAuthor5(String author5){
        this.author5=author5;
    }
    String getAuthor5()
    {
        return author5;
    }
    void setPuburl5(String puburl5){
        this.puburl5=puburl5;
    }
    String getPuburl5()
    {
        return puburl5;
    }
    void setPubdescription5(String pubdescription5){
        this.pubdescription5=pubdescription5;
    }
    String getPubdescription5()
    {
        return pubdescription5;
    }
    void setPubtitle6(String pubtitle6){
        this.pubtitle6=pubtitle6;
    }
    String getPubtitle6()
    {
        return pubtitle6;
    }
    void setPublication6(String publication6){
        this.publication6=publication6;
    }
    String getPublication6()
    {
        return publication6;
    }
    void setAuthor6(String author6){
        this.author6=author6;
    }
    String getAuthor6()
    {
        return author6;
    }
    void setPuburl6(String puburl6){
        this.puburl6=puburl6;
    }
    String getPuburl6()
    {
        return puburl6;
    }
    void setPubdescription6(String pubdescription6){
        this.pubdescription6=pubdescription6;
    }
    String getPubdescription6()
    {
        return pubdescription6;
    }
    void setPubtitle7(String pubtitle7){
        this.pubtitle7=pubtitle7;
    }
    String getPubtitle7()
    {
        return pubtitle7;
    }
    void setPublication7(String publication7){
        this.publication7=publication7;
    }
    String getPublication7()
    {
        return publication7;
    }
    void setAuthor7(String author7){
        this.author7=author7;
    }
    String getAuthor7()
    {
        return author7;
    }
    void setPuburl7(String puburl7){
        this.puburl7=puburl7;
    }
    String getPuburl7()
    {
        return puburl7;
    }
    void setPubdescription7(String pubdescription7){
        this.pubdescription7=pubdescription7;
    }
    String getPubdescription7()
    {
        return pubdescription7;
    }
    void setPubtitle8(String pubtitle8){
        this.pubtitle8=pubtitle8;
    }
    String getPubtitle8()
    {
        return pubtitle8;
    }
    void setPublication8(String publication8){
        this.publication8=publication8;
    }
    String getPublication8()
    {
        return publication8;
    }
    void setAuthor8(String author8){
        this.author8=author8;
    }
    String getAuthor8()
    {
        return author8;
    }
    void setPuburl8(String puburl8){
        this.puburl8=puburl8;
    }
    String getPuburl8()
    {
        return puburl8;
    }
    void setPubdescription8(String pubdescription8){
        this.pubdescription8=pubdescription8;
    }
    String getPubdescription8()
    {
        return pubdescription8;
    }
    void setPubtitle9(String pubtitle9){
        this.pubtitle9=pubtitle9;
    }
    String getPubtitle9()
    {
        return pubtitle9;
    }
    void setPublication9(String publication9){
        this.publication9=publication9;
    }
    String getPublication9()
    {
        return publication9;
    }
    void setAuthor9(String author9){
        this.author9=author9;
    }
    String getAuthor9()
    {
        return author9;
    }
    void setPuburl9(String puburl9){
        this.puburl9=puburl9;
    }
    String getPuburl9()
    {
        return puburl9;
    }
    void setPubdescription9(String pubdescription9){
        this.pubdescription9=pubdescription9;
    }
    String getPubdescription9()
    {
        return pubdescription9;
    }
    void setPubtitle10(String pubtitle10){
        this.pubtitle10=pubtitle10;
    }
    String getPubtitle10()
    {
        return pubtitle10;
    }
    void setPublication10(String publication10){
        this.publication10=publication10;
    }
    String getPublication10()
    {
        return publication10;
    }
    void setAuthor10(String author10){
        this.author10=author10;
    }
    String getAuthor10()
    {
        return author10;
    }
    void setPuburl10(String puburl10){
        this.puburl10=puburl10;
    }
    String getPuburl10()
    {
        return puburl10;
    }
    void setPubdescription10(String pubdescription10){
        this.pubdescription10=pubdescription10;
    }
    String getPubdescription10()
    {
        return pubdescription10;
    }
    void setPublicationdate1(String publicationdate1){
        this.publicationdate1=publicationdate1;
    }
    String getPublicationdate1()
    {
        return publicationdate1;
    }
    void setPublicationdate2(String publicationdate2){
        this.publicationdate2=publicationdate2;
    }
    String getPublicationdate2()
    {
        return publicationdate2;
    }
    void setPublicationdate3(String publicationdate3){
        this.publicationdate3=publicationdate3;
    }
    String getPublicationdate3()
    {
        return publicationdate3;
    }
    void setPublicationdate4(String publicationdate4){
        this.publicationdate4=publicationdate4;
    }
    String getPublicationdate4()
    {
        return publicationdate4;
    }
    void setPublicationdate5(String publicationdate5){
        this.publicationdate5=publicationdate5;
    }
    String getPublicationdate5()
    {
        return publicationdate5;
    }
    void setPublicationdate6(String publicationdate6){
        this.publicationdate6=publicationdate6;
    }
    String getPublicationdate6()
    {
        return publicationdate6;
    }
    void setPublicationdate7(String publicationdate7){
        this.publicationdate7=publicationdate7;
    }
    String getPublicationdate7()
    {
        return publicationdate7;
    }
    void setPublicationdate8(String publicationdate8){
        this.publicationdate8=publicationdate8;
    }
    String getPublicationdate8()
    {
        return publicationdate8;
    }
    void setPublicationdate9(String publicationdate9){
        this.publicationdate9=publicationdate9;
    }
    String getPublicationdate9()
    {
        return publicationdate9;
    }
    void setPublicationdate10(String publicationdate10){ this.publicationdate10=publicationdate10; }
    String getPublicationdate10(){return publicationdate10;}
    //
    void setEmail2(String email2){ this.email2=email2; }
    String getEmail2()
    {
        return email2;
    }
    void setAddressline1(String addressline1){ this.addressline1=addressline1; }
    String getAddressline1(){ return addressline1; }
    void setAddressline2(String addressline2){ this.addressline2=addressline2; }
    String getAddressline2(){ return addressline2; }
    void setAddressline3(String addressline3){ this.addressline3=addressline3; }
    String getAddressline3(){return addressline3; }
    void setTelephone(String telephone){ this.telephone=telephone; }
    String getTelephone(){ return telephone; }
    void setMobile2(String mobile2){ this.mobile2=mobile2; }
    String getMobile2(){ return mobile2; }


    //experiences
    void setPost1e(String post1){
        this.post1=post1;
    }
    String getPost1e()
    {
        return post1;
    }

    void setInst1e(String inst1){
        this.inst1=inst1;
    }
    String getInst1e()
    {
        return inst1;
    }

    void setFromdate1e(String fromdate1){
        this.fromdate1=fromdate1;
    }
    String getFromdate1e()
    {
        return fromdate1;
    }

    void setTodate1e(String todate1){
        this.todate1=todate1;
    }
    String getTodate1e()
    {
        return todate1;
    }

    void setPost2e(String post2){
        this.post2=post2;
    }
    String getPost2e()
    {
        return post2;
    }
    void setInst2e(String inst2){
        this.inst2=inst2;
    }
    String getInst2e()
    {
        return inst2;
    }

    void setFromdate2e(String fromdate2){
        this.fromdate2=fromdate2;
    }
    String getFromdate2e()
    {
        return fromdate2;
    }

    void setTodate2e(String todate2){
        this.todate2=todate2;
    }
    String getTodate2e()
    {
        return todate2;
    }
    void setPost3e(String post3){
        this.post3=post3;
    }
    String getPost3e()
    {
        return post3;
    }

    void setInst3e(String inst3){
        this.inst3=inst3;
    }
    String getInst3e()
    {
        return inst3;
    }

    void setFromdate3e(String fromdate3){
        this.fromdate3=fromdate3;
    }
    String getFromdate3e()
    {
        return fromdate3;
    }

    void setTodate3e(String todate3){
        this.todate3=todate3;
    }
    String getTodate3e()
    {
        return todate3;
    }
    void setPost4e(String post4){
        this.post4=post4;
    }
    String getPost4e()
    {
        return post4;
    }

    void setInst4e(String inst4){
        this.inst4=inst4;
    }
    String getInst4e()
    {
        return inst4;
    }

    void setFromdate4e(String fromdate4){
        this.fromdate4=fromdate4;
    }
    String getFromdate4e()
    {
        return fromdate4;
    }

    void setTodate4e(String todate4){
        this.todate4=todate4;
    }
    String getTodate4e()
    {
        return todate4;
    }

    void setPost5e(String post5){
        this.post5=post5;
    }
    String getPost5e()
    {
        return post5;
    }

    void setInst5e(String inst5){
        this.inst5=inst5;
    }
    String getInst5e()
    {
        return inst5;
    }

    void setFromdate5e(String fromdate5){
        this.fromdate5=fromdate5;
    }
    String getFromdate5e()
    {
        return fromdate5;
    }

    void setTodate5e(String todate5){
        this.todate5=todate5;
    }
    String getTodate5e()
    {
        return todate5;
    }
    void setPost6e(String post6){
        this.post6=post6;
    }
    String getPost6e()
    {
        return post6;
    }

    void setInst6e(String inst6){
        this.inst6=inst6;
    }
    String getInst6e()
    {
        return inst6;
    }

    void setFromdate6e(String fromdate6){
        this.fromdate6=fromdate6;
    }
    String getFromdate6e()
    {
        return fromdate6;
    }

    void setTodate6e(String todate6){
        this.todate6=todate6;
    }
    String getTodate6e()
    {
        return todate6;
    }

    void setPost7e(String post7){
        this.post7=post7;
    }
    String getPost7e()
    {
        return post7;
    }

    void setInst7e(String inst7){
        this.inst7=inst7;
    }
    String getInst7e()
    {
        return inst7;
    }

    void setFromdate7e(String fromdate7){
        this.fromdate7=fromdate7;
    }
    String getFromdate7e()
    {
        return fromdate7;
    }

    void setTodate7e(String todate7){
        this.todate7=todate7;
    }
    String getTodate7e()
    {
        return todate7;
    }


    void setPost8e(String post8){
        this.post8=post8;
    }
    String getPost8e()
    {
        return post8;
    }

    void setInst8e(String inst8){
        this.inst8=inst8;
    }
    String getInst8e()
    {
        return inst8;
    }

    void setFromdate8e(String fromdate8){
        this.fromdate8=fromdate8;
    }
    String getFromdate8e()
    {
        return fromdate8;
    }

    void setTodate8e(String todate8){
        this.todate8=todate8;
    }
    String getTodate8e()
    {
        return todate8;
    }

    void setPost9e(String post9){
        this.post9=post9;
    }
    String getPost9e()
    {
        return post9;
    }

    void setInst9e(String inst9){
        this.inst9=inst9;
    }
    String getInst9e()
    {
        return inst9;
    }

    void setFromdate9e(String fromdate9){
        this.fromdate9=fromdate9;
    }
    String getFromdate9e()
    {
        return fromdate9;
    }

    void setTodate9e(String todate9){
        this.todate9=todate9;
    }
    String getTodate9e()
    {
        return todate9;
    }
    void setPost10e(String post10){
        this.post10=post10;
    }
    String getPost10e()
    {
        return post10;
    }

    void setInst10e(String inst10){
        this.inst10=inst10;
    }
    String getInst10e()
    {
        return inst10;
    }

    void setFromdate10e(String fromdate10){
        this.fromdate10=fromdate10;
    }
    String getFromdate10e()
    {
        return fromdate10;
    }

    void setTodate10e(String todate10){
        this.todate10=todate10;
    }
    String getTodate10e()
    {
        return todate10;
    }

public static void setAdminDataNull(){
    fname=null;
    mname=null;
    lname=null;
    country=null;
    state=null;
    city=null;
    phone=null;
    institute=null;

    instemail=null;
    instweb=null;
    instphone=null;
    instaltrphone=null;
    univname=null;
    instregno=null;

    lang1=null;
    proficiency1=null;
    lang2=null;
    proficiency2=null;
    lang3=null;
    proficiency3=null;
    lang4=null;
    proficiency4=null;
    lang5=null;
    proficiency5=null;
    lang6=null;
    proficiency6=null;
    lang7=null;
    proficiency7=null;
    lang8=null;
    proficiency8=null;
    lang9=null;
    proficiency9=null;
    lang10=null;
    proficiency10=null;

    skill1=null;
    skill2=null;
    skill3=null;
    skill4=null;
    skill5=null;
    skill6=null;
    skill7=null;
    skill8=null;
    skill9=null;
    skill10=null;
    skill11=null;
    skill12=null;
    skill13=null;
    skill14=null;
    skill15=null;
    skill16=null;
    skill17=null;
    skill18=null;
    skill19=null;
    skill20=null;
    sproficiency1=null;
    sproficiency2=null;
    sproficiency3=null;
    sproficiency4=null;
    sproficiency5=null;
    sproficiency6=null;
    sproficiency7=null;
    sproficiency8=null;
    sproficiency9=null;
    sproficiency10=null;
    sproficiency11=null;
    sproficiency12=null;
    sproficiency13=null;
    sproficiency14=null;
    sproficiency15=null;
    sproficiency16=null;
    sproficiency17=null;
    sproficiency18=null;
    sproficiency19=null;
    sproficiency20=null;

    htitle1=null;
    hissuer1=null;
    hdescription1=null;
    htitle2=null;
    hissuer2=null;
    hdescription2=null;
    htitle3=null;
    hissuer3=null;
    hdescription3=null;
    htitle4=null;
    hissuer4=null;
    hdescription4=null;
    htitle5=null;
    hissuer5=null;
    hdescription5=null;
    htitle6=null;
    hissuer6=null;
    hdescription6=null;
    htitle7=null;
    hissuer7=null;
    hdescription7=null;
    htitle8=null;
    hissuer8=null;
    hdescription8=null;
    htitle9=null;
    hissuer9=null;
    hdescription9=null;
    htitle10=null;
    hissuer10=null;
    hdescription10=null;
    yearofhonor1=null;
    yearofhonor2=null;
    yearofhonor3=null;
    yearofhonor4=null;
    yearofhonor5=null;
    yearofhonor6=null;
    yearofhonor7=null;
    yearofhonor8=null;
    yearofhonor9=null;
    yearofhonor10=null;

    ptitle1=null;
    pappno1=null;
    pinventor1=null;
    pissue1=null;
    pfiling1=null;
    purl1=null;
    pdescription1=null;
    ptitle2=null;
    pappno2=null;
    pinventor2=null;
    pissue2=null;
    pfiling2=null;
    purl2=null;
    pdescription2=null;
    ptitle3=null;
    pappno3=null;
    pinventor3=null;
    pissue3=null;
    pfiling3=null;
    purl3=null;
    pdescription3=null;
    ptitle4=null;
    pappno4=null;
    pinventor4=null;
    pissue4=null;
    pfiling4=null;
    purl4=null;
    pdescription4=null;
    ptitle5=null;
    pappno5=null;
    pinventor5=null;
    pissue5=null;
    pfiling5=null;
    purl5=null;
    pdescription5=null;
    ptitle6=null;
    pappno6=null;
    pinventor6=null;
    pissue6=null;
    pfiling6=null;
    purl6=null;
    pdescription6=null;
    ptitle7=null;
    pappno7=null;
    pinventor7=null;
    pissue7=null;
    pfiling7=null;
    purl7=null;
    pdescription7=null;
    ptitle8=null;
    pappno8=null;
    pinventor8=null;
    pissue8=null;
    pfiling8=null;
    purl8=null;
    pdescription8=null;
    ptitle9=null;
    pappno9=null;
    pinventor9=null;
    pissue9=null;
    pfiling9=null;
    purl9=null;
    pdescription9=null;
    ptitle10=null;
    pappno10=null;
    pinventor10=null;
    pissue10=null;
    pfiling10=null;
    purl10=null;
    pdescription10=null;
    pselectedcountry1=null;
    pselectedcountry2=null;
    pselectedcountry3=null;
    pselectedcountry4=null;
    pselectedcountry5=null;
    pselectedcountry6=null;
    pselectedcountry7=null;
    pselectedcountry8=null;
    pselectedcountry9=null;
    pselectedcountry10=null;
    issuedorpending1=null;
    issuedorpending2=null;
    issuedorpending3=null;
    issuedorpending4=null;
    issuedorpending5=null;
    issuedorpending6=null;
    issuedorpending7=null;
    issuedorpending8=null;
    issuedorpending9=null;
    issuedorpending10=null;

    pubtitle1=null;
    publication1=null;
    author1=null;
    puburl1=null;
    pubdescription1=null;
    pubtitle2=null;
    publication2=null;
    author2=null;
    puburl2=null;
    pubdescription2=null;
    pubtitle3=null;
    publication3=null;
    author3=null;
    puburl3=null;
    pubdescription3=null;
    pubtitle4=null;
    publication4=null;
    author4=null;
    puburl4=null;
    pubdescription4=null;
    pubtitle5=null;
    publication5=null;
    author5=null;
    puburl5=null;
    pubdescription5=null;
    pubtitle6=null;
    publication6=null;
    author6=null;
    puburl6=null;
    pubdescription6=null;
    pubtitle7=null;
    publication7=null;
    author7=null;
    puburl7=null;
    pubdescription7=null;
    pubtitle8=null;
    publication8=null;
    author8=null;
    puburl8=null;
    pubdescription8=null;
    pubtitle9=null;
    publication9=null;
    author9=null;
    puburl9=null;
    pubdescription9=null;
    pubtitle10=null;
    publication10=null;
    author10=null;
    puburl10=null;
    pubdescription10=null;
    publicationdate1=null;
    publicationdate2=null;
    publicationdate3=null;
    publicationdate4=null;
    publicationdate5=null;
    publicationdate6=null;
    publicationdate7=null;
    publicationdate8=null;
    publicationdate9=null;
    publicationdate10=null;

    email2=null;
    addressline1=null;
    addressline2=null;
    addressline3=null;
    telephone=null;
    mobile2=null;

    post1=null;
    inst1=null;
    post2=null;
    inst2=null;
    post3=null;
    inst3=null;
    post4=null;
    inst4=null;
    post5=null;
    inst5=null;
    post6=null;
    inst6=null;
    post7=null;
    inst7=null;
    post8=null;
    inst8=null;
    post9=null;
    inst9=null;
    post10=null;
    inst10=null;

    fromdate1=null;
    todate1=null;
    fromdate2=null;
    todate2=null;
    fromdate3=null;
    todate3=null;
    fromdate4=null;
    todate4=null;
    fromdate5=null;
    todate5=null;
    fromdate6=null;
    todate6=null;
    fromdate7=null;
    todate7=null;
    fromdate8=null;
    todate8=null;
    fromdate9=null;
    todate9=null;
    fromdate10=null;
    todate10=null;


}



}


