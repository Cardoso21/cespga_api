package br.com.cespga.date.vo.v1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CargoVO extends RepresentationModel<CargoVO> implements Serializable {
    private static final long serialVersion = 1L;

    private Long id;
    private String cargo;
}
