package addressbook;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AddressBook implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "addressBook")
    private List <BuddyInfo> buddies;

    public AddressBook(){}

    public AddressBook(Long id) {
        this.id = id;
        this.buddies = new ArrayList<BuddyInfo>();
    }

    public Long getId(){ return id; }

    // Adds a buddy to the list of buddies
    public boolean addBuddy(BuddyInfo buddyInfo){
        if ( buddyInfo != null){
            for (BuddyInfo b: this.buddies){
                if(buddyInfo.equals((b))){
                    System.out.println("Buddy already exist in this Address Book!");
                    return false;
                }
            }
            buddies.add(buddyInfo);
            System.out.println("Your buddy is Added successfully!");
        }
        return false;
    }

    // Removes a buddy from the Address Book
    public BuddyInfo removeBuddy(BuddyInfo buddy) {
        if (buddy != null) {
            buddies.remove(buddy);
            System.out.println("Removed your Buddy!");
            return buddy;
        }
        return null;
    }

    public void setBuddies(List<BuddyInfo> buddies){
        this.buddies = buddies;
    }

    @OneToMany
    public List<BuddyInfo> getBuddies() {
        return buddies;
    }

    public BuddyInfo getBuddy(String buddyName){
        for(BuddyInfo b: buddies){
            if(b.getName().equals(buddyName)) return b;
        }
        System.out.println("Your Buddy %s is not in your address book" + buddyName);
        return null;
    }

//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + ((buddies == null) ? 0 : buddies.hashCode());
//        return result;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj)
//            return true;
//        if (obj == null)
//            return false;
//        if (getClass() != obj.getClass())
//            return false;
//        AddressBook other = (AddressBook) obj;
//        if (id == null) {
//            if (other.id != null)
//                return false;
//        } else if (!id.equals(other.id))
//            return false;
//        if (buddies == null) {
//            if (other.buddies != null)
//                return false;
//        } else if (!buddies.equals(other.buddies))
//            return false;
//        return true;
//    }

    @Override
    public String toString() {
        return "AddressBook{" +
                "buddies=" + buddies +
                '}';
    }
}


