package nazeem.web.model;

import javax.persistence.*;

@Entity
@Table(name="warden")
public class Warden {

    protected Warden() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "warden_id")
    private Long id;


    public Long getId() {
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }

    @Column(name = "name")
    private String name;

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }

    @Column(name = "hostel_id")
    private long hostelId;

    public long getHostelId(){
        return this.hostelId;
    }
    public void setHostelId(long hostelId){
        this.hostelId=hostelId;
    }


}