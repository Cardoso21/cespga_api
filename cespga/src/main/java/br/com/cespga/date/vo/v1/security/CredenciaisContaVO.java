package br.com.cespga.date.vo.v1.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CredenciaisContaVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
}
