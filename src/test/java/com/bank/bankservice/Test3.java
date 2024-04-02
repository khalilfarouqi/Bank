package com.bank.bankservice;

import com.bank.bankservice.jwt.TokenManager;

public class Test3 {
    public static final String TOKEN_1 =
            "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcxMjA2NzI4NiwiZXhwIjoxNzEyMTUzNjg2fQ.-FPDfNOj-gPu5EPvMrdmCmBFq2ipn6Xa0KOM13zzoeZWBbxLcrEpFnucISCVr6YxUcGTkndbjGMc_T8xDACj6g";

    public static final String TOKEN_2 =
            "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGVzIjpbIkFETUlOIiwiQ0xJRU5UIl0sImV4cCI6MTcxMjE1MzcwNiwiaWF0IjoxNzEyMDY3MzA2fQ.72Fp5wqM13KG9zhVyJyE5rDVoiFc90vnTF92I_BZI0SBj7gMBoEgR6sDbTBQCMzwcaqonGjQi07vKysEwCQD-Q";

    public static void main(String[] args) {
        try {
            boolean isToken1Valid = TokenManager.validateJwtToken(TOKEN_1);
            boolean isToken2Valid = TokenManager.validateJwtToken(TOKEN_2);
            System.out.println(isToken1Valid);
            System.out.println(isToken2Valid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
