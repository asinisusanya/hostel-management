package nazeem.web.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="hostel")
public class Hostel {

    protected Hostel() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hostel_id")
    private Long id;


    public Long getId() {
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }



    @NotEmpty(message = "Name can't be empty!")
    @Column(name = "name")
    private String name;

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }

    @Column(name = "location")
    private String location;

    public String getLocation(){
        return this.location;
    }
    public void setLocation(String location){
        this.location=location;
    }

    @Column(name = "hostelType")
    private String hostelType;

    public String getHostelType(){
        return this.hostelType;
    }
    public void setHostelType(String hostelType){
        this.hostelType=hostelType;
    }


    // other getters and setters are hidden for brevity
}