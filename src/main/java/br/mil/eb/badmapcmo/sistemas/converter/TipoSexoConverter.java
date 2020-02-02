package br.mil.eb.badmapcmo.sistemas.converter;

import org.springframework.core.convert.converter.Converter;

import br.mil.eb.badmapcmo.sistemas.domain.enumeration.SexoEnum;

public class TipoSexoConverter implements Converter<String, SexoEnum> {

	@Override
	public SexoEnum convert(String texto) {
		char tipo = texto.charAt(0);
		return tipo == SexoEnum.FEMININO.getDescricao() ? SexoEnum.FEMININO : SexoEnum.MASCULINO;
	}

}
