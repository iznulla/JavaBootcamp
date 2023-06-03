package day01.ex02;


public class Program {
    public static void main(String[] args) {
        User ayrat = new User( "Ayrat", 2000 );
        User sasa = new User( "Sasa", 3000);

        UsersArrayList userlst = new UsersArrayList();
        userlst.addUser(ayrat);
        userlst.addUser(sasa);



        System.out.println("ID - " + userlst.getUserById(1).getId() +
                ", name - " + userlst.getUserById(1).getName() +
                ", balance - " + userlst.getUserById(1).getBalance());

        System.out.println("ID - " + userlst.getUserByIndex(1).getId() +
                ", name - " + userlst.getUserByIndex(1).getName() +
                ", balance - " + userlst.getUserByIndex(1).getBalance());
    }
}
