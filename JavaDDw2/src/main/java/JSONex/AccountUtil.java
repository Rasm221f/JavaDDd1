package JSONex;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;

public class AccountUtil {
    private static final Gson gson = new Gson();

    public static List<Account> readAccountsFromFile(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            Type listType = new TypeToken<ArrayList<Account>>(){}.getType();
            return gson.fromJson(reader, listType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<AccountDTO> convertToDTOs(List<Account> accounts) {
        List<AccountDTO> dtos = new ArrayList<>();
        for (Account account : accounts) {
            AccountDTO dto = new AccountDTO();
            dto.setFullName(account.getFirstName() + " " + account.getLastName());
            dto.setCity(account.getAddress().getCity());
            dto.setZipCode(String.valueOf(account.getAddress().getZipCode()));
            dto.setIsActive(account.getAccount().isActive() ? "Active" : "Inactive");
            dtos.add(dto);
        }
        return dtos;
    }

    public static void printDTOs(List<AccountDTO> dtos) {
        for (AccountDTO dto : dtos) {
            System.out.println(dto);
        }
    }
}
