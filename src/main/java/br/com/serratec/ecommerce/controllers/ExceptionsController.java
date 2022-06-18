package br.com.serratec.ecommerce.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.serratec.ecommerce.exceptions.CategoriaExistenteException;
import br.com.serratec.ecommerce.exceptions.CategoriaInexistenteException;
import br.com.serratec.ecommerce.exceptions.ClienteExistenteException;
import br.com.serratec.ecommerce.exceptions.ClienteInexistenteException;
import br.com.serratec.ecommerce.exceptions.EnderecoExistenteException;
import br.com.serratec.ecommerce.exceptions.EnderecoInexistenteException;
import br.com.serratec.ecommerce.exceptions.FuncionarioExistenteException;
import br.com.serratec.ecommerce.exceptions.FuncionarioInexistenteException;
import br.com.serratec.ecommerce.exceptions.ImagemExistenteException;
import br.com.serratec.ecommerce.exceptions.ImagemInexistenteException;
import br.com.serratec.ecommerce.exceptions.PedidoExistenteException;
import br.com.serratec.ecommerce.exceptions.PedidoInexistenteException;
import br.com.serratec.ecommerce.exceptions.ProdutoExistenteException;
import br.com.serratec.ecommerce.exceptions.ProdutoInexistenteException;
import br.com.serratec.ecommerce.exceptions.QntEstoqueInsuficienteException;
import br.com.serratec.ecommerce.exceptions.UsuarioExistenteException;
import br.com.serratec.ecommerce.exceptions.UsuarioInexistenteException;

@RestControllerAdvice
public class ExceptionsController {
	
	@ExceptionHandler(CategoriaExistenteException.class)
	public ResponseEntity<String> handleCategoriaExistente() {
		String msg = "Categoria já existe";
		return ResponseEntity.badRequest().header("errorMsg", msg).build();
	}
	
	@ExceptionHandler(CategoriaInexistenteException.class)
	public ResponseEntity<String> handleCategoriaInexistente() {
		String msg = "Categoria não existe";
		return ResponseEntity.notFound().header("errorMsg", msg).build();
	}
	
	@ExceptionHandler(ClienteExistenteException.class)
	public ResponseEntity<String> handleClienteExistente() {
		String msg = "Cliente já existe";
		return ResponseEntity.badRequest().header("errorMsg", msg).build();
	}
	
	@ExceptionHandler(ClienteInexistenteException.class)
	public ResponseEntity<String> handleClienteInexistente() {
		String msg = "Cliente não existe";
		return ResponseEntity.notFound().header("errorMsg", msg).build();
	}
	
	@ExceptionHandler(EnderecoExistenteException.class)
	public ResponseEntity<String> handleEnderecoExistente() {
		String msg = "Endereco já existe";
		return ResponseEntity.badRequest().header("errorMsg", msg).build();
	}
	
	@ExceptionHandler(EnderecoInexistenteException.class)
	public ResponseEntity<String> handleEnderecoInexistente() {
		String msg = "Endereco não existe";
		return ResponseEntity.notFound().header("errorMsg", msg).build();
	}
	
	@ExceptionHandler(FuncionarioExistenteException.class)
	public ResponseEntity<String> handleFuncionarioExistente() {
		String msg = "Funcionario já existe";
		return ResponseEntity.badRequest().header("errorMsg", msg).build();
	}
	
	@ExceptionHandler(FuncionarioInexistenteException.class)
	public ResponseEntity<String> handleFuncionarioInexistente() {
		String msg = "Funcionario não existe";
		return ResponseEntity.notFound().header("errorMsg", msg).build();
	}
	
	@ExceptionHandler(ProdutoExistenteException.class)
	public ResponseEntity<String> handleProdutoExistente() {
		String msg = "Produto já existe";
		return ResponseEntity.badRequest().header("errorMsg", msg).build();
	}
	
	@ExceptionHandler(ProdutoInexistenteException.class)
	public ResponseEntity<String> handleProdutoInexistente() {
		String msg = "Produto não existe";
		return ResponseEntity.notFound().header("errorMsg", msg).build();
	}
	
	@ExceptionHandler(UsuarioExistenteException.class)
	public ResponseEntity<String> handleUsuarioExistente() {
		String msg = "Usuario já existe";
		return ResponseEntity.badRequest().header("errorMsg", msg).build();
	}
	
	@ExceptionHandler(UsuarioInexistenteException.class)
	public ResponseEntity<String> handleUsuarioInexistente() {
		String msg = "Usuario não existe";
		return ResponseEntity.notFound().header("errorMsg", msg).build();
	}
	
	@ExceptionHandler(PedidoExistenteException.class)
	public ResponseEntity<String> handlePedidoExistente() {
		String msg = "Pedido já existe";
		return ResponseEntity.badRequest().header("errorMsg", msg).build();
	}
	
	@ExceptionHandler(PedidoInexistenteException.class)
	public ResponseEntity<String> handlePedidoInexistente() {
		String msg = "Pedido não existe";
		return ResponseEntity.notFound().header("errorMsg", msg).build();
	}
	
	@ExceptionHandler(ImagemExistenteException.class)
	public ResponseEntity<String> handleImagemExistente() {
		String msg = "Imagem já existe";
		return ResponseEntity.badRequest().header("errorMsg", msg).build();
	}
	
	@ExceptionHandler(ImagemInexistenteException.class)
	public ResponseEntity<String> handleImagemInexistente() {
		String msg = "Imagem não existe";
		return ResponseEntity.notFound().header("errorMsg", msg).build();
	}
	
	@ExceptionHandler(QntEstoqueInsuficienteException.class)
	public ResponseEntity<String> handleQntEstoqueInsuficienteInexistente() {
		String msg = "Quantidade em estoque insuficiente";
		return ResponseEntity.badRequest().header("errorMsg", msg).build();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidacao(MethodArgumentNotValidException ex) {
		Map<String, String> errosMap = new HashMap<>();
		List<ObjectError> erros = ex.getBindingResult().getAllErrors();
		
		for(ObjectError erro : erros) {
			String atributo = ((FieldError) erro).getField();
			String msg = erro.getDefaultMessage();
			errosMap.put(atributo, msg);
		}
		
		return new ResponseEntity<Map<String, String>>(errosMap, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<String> handleConstraintViolationInexistente() {
		String msg = "campo inválido";
		return ResponseEntity.badRequest().header("errorMsg", msg).build();
	}
}
