import java.sql.*;
import java.util.*;




public class EncryptionProgram {

    private Scanner scanner;
    private Random random;
    private ArrayList<Character> list;
    private  ArrayList<Character> shuffleList;
    private char character;
    private  String line;
    private char[] letters;
    private char[] secretLetter;
    EncryptionProgram() throws SQLException {
        scanner = new Scanner(System.in);
        Random random = new Random();
        list = new ArrayList<>();
        shuffleList = new ArrayList<>();
        character = ' ';

        newKey();
        askQuestion();

    }
    private void askQuestion(){
        while(true){
            System.out.println("*******************************************");
            System.out.println("What Do You Want to do");
            System.out.println("(N)ewKey,(G)etKey,(E)ncrypt,(D)ecrypt,(T)acty,(Q)uite");
            char response = Character.toUpperCase(scanner.nextLine().charAt(0));

            switch (response){
                case 'N':
                    newKey();
                    break;
                case 'G':
                    getKey();
                    break;
                case 'E':
                    encrypt();
                    break;
                case 'D':
                    decrypt();
                    break;
                case 'Q':
                    quite();
                    break;
                case 'T':
                    try {
                        getConnect();
                        System.out.println("Get Connected");
                    } catch (SQLException e) {
                        System.out.println(e);
                    }
                    break;
                default:
                    System.out.println("Invalid Answer :(");
            }
        }

    }

    public void getData(){
        String url = "jdbc:postgresql://localhost:5432/EncryptData";
        String user = "postgres";
        String password = "postgres";


        Connection con = null;
        try {
            con = DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            Statement st = con.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void getConnect() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/EncryptData";
        String user = "postgres";
        String password = "postgres";


        Connection con = DriverManager.getConnection(url,user,password);
        Statement st = con.createStatement();
        try {
            String sqlcmd = "INSERT INTO public.\"HashTable\"(\"Text\",\"Code\")VALUES('79P=9^p79P=9^p','Nico')";
            st.executeUpdate(sqlcmd);
            System.out.println("Insert Succesfully");
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }


    private void dbgetkey(){

    }
    private void newKey(){
        character = ' ';
        list.clear();
        shuffleList.clear();

        for(int i = 32;i<127;i++){
            list.add(character);
            character++;
        }

        shuffleList = new ArrayList(list);
        Collections.shuffle(shuffleList);
        System.out.println("*A new key has been generated*");
    }
    private void getKey(){
        System.out.println("Key: ");
        for(Character x: list){
            System.out.print(x);
        }
        System.out.println();
        for(Character x: shuffleList){
            System.out.print(x);
        }
        System.out.println();
    }
    private void encrypt(){
        System.out.println("Enter a Message to be encrypted: ");
        String message = scanner.nextLine();

        letters = message.toCharArray();

        for(int i=0;i<letters.length;i++){
            for(int j=0;j<list.size();j++){
                if(letters[i] == list.get(j)){
                    letters[i]=shuffleList.get(j);
                    break;
                }
            }
        }
        System.out.println("Encrypted: ");
        for(char x: letters){
            System.out.print(x);
        }
        System.out.println(letters);
        System.out.println();
    }
    private void decrypt(){
        System.out.println("Enter a Message to be decrypted: ");
        String message = scanner.nextLine();

        letters = message.toCharArray();

        for(int i=0;i<letters.length;i++){
            for(int j=0;j<shuffleList.size();j++){
                if(letters[i] == shuffleList.get(j)){
                    letters[i]=list.get(j);
                    break;
                }
            }
        }
        System.out.println("Decrypted: ");
        for(char x: letters){
            System.out.print(x);
        }
        System.out.println();
    }
    private void quite() {
        System.out.println("Cipher Text Programs Thanks");
        System.exit(0);
    }
}
