package addressbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @Autowired
    AddressBookRepository repository;

    @GetMapping("/")
    public String index(Model model){
        AddressBook addressBook = new AddressBook(new Long(1));
        model.addAttribute("addressbook", addressBook);
        model.addAttribute("newBuddy", new BuddyInfo());
        return "addressBook";
    }
    @PostMapping("/")
    public String addBuddy(@ModelAttribute BuddyInfo buddyInfo, Model model) {
        AddressBook addressBook  = repository.findById(new Long(1)).orElse(new AddressBook(new Long(1)));
        addressBook.addBuddy(buddyInfo);
        buddyInfo.setAddressBook(addressBook);
        repository.save(addressBook);

        model.addAttribute("addressbook", addressBook);
        model.addAttribute("newBuddy", new BuddyInfo());
        return "addressbook";
    }
}
