package JSONex;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\RAIJ\\IdeaProjects\\JavaDDw2\\src\\main\\resources\\accounts.json";
        List<Account> accounts = AccountUtil.readAccountsFromFile(filePath);
        if (accounts != null) {
            List<AccountDTO> dtos = AccountUtil.convertToDTOs(accounts);
            AccountUtil.printDTOs(dtos);
        } else {
            System.out.println("Failed to read accounts from file.");
        }
    }
}
