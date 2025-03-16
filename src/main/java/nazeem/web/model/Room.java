package nazeem.web.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="room")
public class Room {

    protected Room() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;


    public Long getId() {
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }

    @Column(name = "hostel_id")
    private long hostel_id;

    public long getHostel_id(){
        return this.hostel_id;
    }
    public void setHostel_id(long hostel_id){
        this.hostel_id=hostel_id;
    }

    @Column(name = "room_no")
    private Long room_no;


    public Long getRoom_no() {
        return room_no;
    }
    public void setRoom_no(Long room_no){
        this.room_no=room_no;
    }

    @Column(name = "wing")
    private Long wing;


    public Long getWing() {
        return wing;
    }
    public void setWing(Long wing){
        this.wing=wing;
    }

    @Column(name = "floor")
    private Long floor;


    public Long getFloor() {
        return floor;
    }
    public void setFloor(Long floor){
        this.floor=floor;
    }

    @Column(name = "type")
    private Long type;


    public Long getType() {
        return type;
    }
    public void setType(Long type){
        this.type=type;
    }

    @Column(name = "capacity")
    private int capacity;

    public int getCapacity(){
        return this.capacity;
    }
    public void setCapacity(int capacity){
        this.capacity=capacity;
    }


    // other getters and setters are hidden for brevity
}