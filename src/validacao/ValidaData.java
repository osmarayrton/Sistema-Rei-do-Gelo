/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author osmar
 */
public class ValidaData {
    
    public boolean data(String data) throws ParseException {
        //SimpleDateFormat é usada para trabalhar com formatação de datas
        //neste caso meu formatador irá trabalhar com o formato "dd/MM/yyyy"
        //dd = dia, MM = mes, yyyy = ano
        //o "M" dessa String é maiusculo porque "m" minusculo se n me engano é minutos
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        //a mágica desse método acontece aqui, pois o setLenient() é usado para setar
        //sua escolha sobre datas estranhas, quando eu seto para "false" estou dizendo
        //que não aceito datas falsas como 31/02/2016
        sdf.setLenient(false);
        //aqui eu tento converter a String em um objeto do tipo date, se funcionar
        //sua data é valida
        sdf.parse(data);
        //se nada deu errado retorna true (verdadeiro)
        return true;
    }
    
    public boolean dataMenorQueHoje(String data) {
        //DateTimeFormatter tem uma função identica ao SimpleDateFormatter, mas escolhi usar ele
        //pois ele trabalha junto com o LocalDate que facilita muito trabalhar com datas
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        //Aqui eu converto uma data em LocalDate e digo que quero no formato do DateTimeFormatter
        //que criei acima
        LocalDate dataVerificada = LocalDate.parse(data, dtf);
        //Esse comando pega a data de hoje
        LocalDate hoje = LocalDate.now();
        //Se dataVerificada comparada com hoje é menor ou igual a zero então retorna verdadeiro,
        //senão, retorna falso
       // com LocalDate não tem como comparar assim: data1 < data2
       //Tem que ser assim: data1.compareTo(data2)
       //quando a data1 é menor isso retorna -1, quando é maior retorna 1, e quando é igual retorna 0
       //por isso eu comparei com <=0 abaixo
        return dataVerificada.compareTo(hoje) <= 0;
    }
    
}
