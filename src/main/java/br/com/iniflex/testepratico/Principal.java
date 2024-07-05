package br.com.iniflex.testepratico;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal { 
    
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>(Arrays.asList(
                new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"),
                new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"),
                new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"),
                new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"),
                new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"),
                new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"),
                new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"),
                new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"),
                new Funcionario("Heloisa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"),
                new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente")
        ));

        // 3.2 - Remover o funcionário “João” da lista
        funcionarios.removeIf(f -> f.getNome().equals("João"));              

        // 3.3 - Imprimir todos os funcionários com todas suas informações
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (Funcionario f : funcionarios) {
            System.out.printf("Nome: %s, Data de Nascimento: %s, Salario: %s, Funcao: %s%n",
                    f.getNome(),
                    f.getDataNascimento().format(formatter),
                    String.format("%,.2f", f.getSalario()),
                    f.getFuncao());
        }
        
        System.out.println("----------------------------");
        
        // 3.4 - Aumento de 10% no salário
        funcionarios.forEach(f -> f.setSalario(f.getSalario().multiply(new BigDecimal("1.10"))));
        for (Funcionario f : funcionarios) {
            System.out.printf("Nome: %s, Salario com aumento de 10%%: %s%n",
                    f.getNome(),                    
                    String.format("%,.2f", f.getSalario()));
        }
        
        System.out.println("----------------------------");

        // 3.5 - Agrupar funcionários por função
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        // 3.6 - Imprimir funcionários agrupados por função
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("Funcao " + funcao + ":");
            lista.forEach(f -> System.out.println(f.getNome()));
        });
        
        System.out.println("----------------------------");

        // 3.8 - Imprimir funcionários que fazem aniversário no mês 10 e 12
        System.out.println("Funcionarios que fazem aniversario em Outubro e Dezembro:");
        funcionarios.stream()
                .filter(f -> f.getDataNascimento().getMonth() == Month.OCTOBER || f.getDataNascimento().getMonth() == Month.DECEMBER)
                .forEach(f -> System.out.println(f.getNome() + " - Aniversario: " + f.getDataNascimento().format(formatter)));

        System.out.println("----------------------------");
        
        // 3.9 - Imprimir o funcionário com a maior idade
        Funcionario maisVelho = Collections.max(funcionarios, Comparator.comparing(Funcionario::getDataNascimento).reversed());
        int idade = LocalDate.now().getYear() - maisVelho.getDataNascimento().getYear();
        System.out.println("Funcionario mais velho: " + maisVelho.getNome() + " - Idade: " + idade);

        System.out.println("----------------------------");
        
        // 3.10 - Imprimir a lista de funcionários por ordem alfabética
        System.out.println("Funcionarios em ordem alfabetica:");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(f -> System.out.println(f.getNome()));
        
        System.out.println("----------------------------");

        // 3.11 - Imprimir o total dos salários dos funcionários
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.printf("Total dos salarios: %,.2f%n", totalSalarios);
        
        System.out.println("----------------------------");

        // 3.12 - Imprimir quantos salários mínimos ganha cada funcionário
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("Salarios minimos por funcionario:");
        for (Funcionario f : funcionarios) {
            BigDecimal salariosMinimos = f.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
            System.out.printf("%s: %,.2f salarios minimos%n", f.getNome(), salariosMinimos);
        }
    }
    
}
