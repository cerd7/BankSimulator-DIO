package org.cerd.bank.operations.account.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.cerd.bank.operations.noncustomer.GenerateHash;

public class User
{
    @JsonProperty("Name")
    private String name;

    @JsonProperty("Age")
    private Integer age;

    @JsonProperty("cellPhone")
    private String cellPhone;

    @JsonProperty("Cpf")
    private String cpf;

    @JsonProperty("hashCode")
    private String hash;


    public User()
    {
    }

    GenerateHash genHash = new GenerateHash();

    public User(String name, Integer age, String cellPhone, String cpf)
    {
        if (cpf == null || cpf.isEmpty()) {
            throw new IllegalArgumentException("CPF não pode ser nulo ou vazio.");
        }
        this.name = name;
        this.age = age;
        this.cellPhone = cellPhone;
        this.cpf = cpf;
        this.hash = genHash.hashGenerate(name, cpf);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getHash() {
        return hash;
    }
}
