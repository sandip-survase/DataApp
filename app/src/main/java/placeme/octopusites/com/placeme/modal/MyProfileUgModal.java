package placeme.octopusites.com.placeme.modal;

import java.io.Serializable;

/**
 * Created by admin on 11/20/2017.
 */

public class MyProfileUgModal implements Serializable {
    public  String markssem1,outofsem1,percentsem1,markssem2,outofsem2,percentsem2,markssem3,outofsem3,percentsem3,markssem4,outofsem4,percentsem4,markssem5,outofsem5,percentsem5,markssem6,outofsem6, percentsem6,markssem7,outofsem7,percentsem7,markssem8,outofsem8,percentsem8,aggregate,schoolname,monthandyearofpassing;
    public String selectedCourse="",selectedStream="",selectedUniversity="";
    public MyProfileUgModal(String markssem1,String outofsem1,String percentsem1,String markssem2,String outofsem2,String percentsem2,String markssem3,String outofsem3,String percentsem3,String markssem4,String outofsem4,String percentsem4,String markssem5,String outofsem5,String percentsem5,String markssem6,String outofsem6,String percentsem6,String markssem7,String outofsem7,String percentsem7,String markssem8,String outofsem8,String percentsem8,String aggregate,String schoolname,String monthandyearofpassing,String selectedCourse,String selectedStream,String selectedUniversity)
    {
        this.markssem1=markssem1;
        this.markssem2= markssem2;
        this.markssem3 = markssem3;
        this.markssem4 =markssem4;
        this.markssem5 =markssem5;
        this.markssem6 =markssem6;
        this.markssem7=markssem7;
        this.markssem8 =markssem8;

        this.outofsem1 =outofsem1;
        this.outofsem2 =outofsem2;
        this.outofsem3 =outofsem3;
        this.outofsem4 =outofsem4;
        this.outofsem5 =outofsem5;
        this.outofsem6 =outofsem6;
        this.outofsem7 =outofsem7;
        this.outofsem8 =outofsem8;

        this.percentsem1 = percentsem1;
        this.percentsem2 = percentsem2;
        this.percentsem3 = percentsem3;
        this.percentsem4 = percentsem4;
        this.percentsem5 = percentsem5;
        this.percentsem6 = percentsem6;
        this.percentsem7 = percentsem7;
        this.percentsem8 = percentsem8;

        this.aggregate = aggregate;
        this.schoolname=schoolname;
        this.monthandyearofpassing = monthandyearofpassing;
        this.selectedCourse = selectedCourse;
        this.selectedStream = selectedStream;
        this.selectedUniversity = selectedUniversity;

    }
}
