package placeme.octopusites.com.placeme.modal;

import java.io.Serializable;

/**
 * Created by admin on 11/20/2017.
 */

public class MyProfileLocationModal implements Serializable {

    public String slocation1,slocation2,slocation3,slocation4,slocation5;
    public MyProfileLocationModal(String slocation1,String slocation2,String slocation3,String slocation4,String slocation5)
    {
        this.slocation1=slocation1;
        this.slocation2=slocation2;
        this.slocation3=slocation3;
        this.slocation4=slocation4;
        this.slocation5=slocation5;
    }
}
