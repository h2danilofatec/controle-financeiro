package com.fatec.controle_financeiro.domain.cliente;

//import com.fatec.controle_financeiro.entities.Cliente;
//import org.springframework.beans.factory.annotation.Autowired;
//import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
   /* @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente salvarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente atualizarCliente(Long id, Cliente cliente) {
        Cliente clienteCadastradoCliente = encontrarClientePorId(id);

        if (clienteCadastradoCliente == null)
        {
            return null;
        } else {
            clienteCadastradoCliente.setName(cliente.getName());
            return clienteRepository.save(clienteCadastradoCliente);
        }
    }

    public Cliente encontrarClientePorId(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public void deletarCliente(Long id) {
        Cliente cliente = encontrarClientePorId(id);

        if(cliente == null)
            throw new IllegalArgumentException("Cliente nao existe");


        clienteRepository.deleteById(id);
    }*/
}

