package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author osmar
 */
@Entity
@Table(name = "venda")
@XmlRootElement
@NamedQueries({
    
    @NamedQuery(name = "Venda.findAll", query = "SELECT v FROM Venda v WHERE V.vndStatus like :vndStatus")
    ,@NamedQuery(name = "Venda.findNaoPagas", query = "SELECT v FROM Venda v WHERE V.vndStatusPagamento like :vndStatusPagamento")
//    ,@NamedQuery(name = "Venda.findByCliente", query = "SELECT  v FROM Venda v WHERE V.clienteCliCodcli =: clienteCliCodcli")
    ,@NamedQuery(name = "Venda.findByVndCodvend", query = "SELECT v FROM Venda v WHERE v.vndCodvend = :vndCodvend")
    , @NamedQuery(name = "Venda.findByVndData", query = "SELECT v FROM Venda v WHERE v.vndData = :vndData")
    , @NamedQuery(name = "Venda.findByVndValor", query = "SELECT v FROM Venda v WHERE v.vndValor = :vndValor")
    , @NamedQuery(name = "Venda.findByVndFormapgto", query = "SELECT v FROM Venda v WHERE v.vndFormapgto = :vndFormapgto")
    , @NamedQuery(name = "Venda.findByVndDesconto", query = "SELECT v FROM Venda v WHERE v.vndDesconto = :vndDesconto")})
public class Venda implements Serializable {

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "vnd_codvend")
    private Integer vndCodvend;
    @Column(name = "vnd_data")
    @Temporal(TemporalType.DATE)
    private Date vndData;
    @Column(name = "vnd_formapgto")
    private String vndFormapgto;
    @Column(name = "vnd_valor")
    private Float vndValor;
    @Column(name = "vnd_desconto")
    private float vndDesconto = 0;
    @Column(name="vnd_status")
    private String vndStatus;
    @Column(name="vnd_statuspagamento")
    private String vndStatusPagamento;

    @JoinColumn(name = "cliente_cli_codcli", referencedColumnName = "cli_codcli")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cliente clienteCliCodcli;
    @JoinColumn(name = "usuario_us_codusuario", referencedColumnName = "us_codusuario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuarioUsCodusuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "venda", fetch = FetchType.LAZY)
    private List<ItemVenda> itemvendaList =  new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "venda", fetch = FetchType.LAZY)
    private List<Pagamento> pagamentoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "venda", fetch = FetchType.LAZY)
    private List<ItemVenda> listaVenda;


    public Venda() {
    }

    public Venda(Date vndData, String vndFormapgto, Float vndValor, Cliente clienteCliCodcli, Usuario usuarioUsCodusuario, List<ItemVenda> listaVenda) {
        this.vndData = vndData;
        this.vndFormapgto = vndFormapgto;
        this.vndValor = vndValor;
        this.clienteCliCodcli = clienteCliCodcli;
        this.usuarioUsCodusuario = usuarioUsCodusuario;
        this.listaVenda = listaVenda;
        for(ItemVenda iv : listaVenda){
            iv.setVenda(this);
        }
    }
    
    

    public Venda(Integer vndCodvend) {
        this.vndCodvend = vndCodvend;
    }

    public Integer getVndCodvend() {
        return vndCodvend;
    }

    public void setVndCodvend(Integer vndCodvend) {
        this.vndCodvend = vndCodvend;
    }

    public Date getVndData() {
        return vndData;
    }

    public void setVndData(Date vndData) {
        this.vndData = vndData;
    }

    public Float getVndValor() {
       

        return vndValor;
    }

    public void setVndValor(Float vndValor) {
        this.vndValor = vndValor;
    }

    public String getVndFormapgto() {
        return vndFormapgto;
    }

    public void setVndFormapgto(String vndFormapgto) {
        this.vndFormapgto = vndFormapgto;
    }

   
    
    

    public Cliente getClienteCliCodcli() {
        return clienteCliCodcli;
    }

    public void setClienteCliCodcli(Cliente clienteCliCodcli) {
        this.clienteCliCodcli = clienteCliCodcli;
    }

    public Usuario getUsuarioUsCodusuario() {
        return usuarioUsCodusuario;
    }

    public void setUsuarioUsCodusuario(Usuario usuarioUsCodusuario) {
        this.usuarioUsCodusuario = usuarioUsCodusuario;
    }

    public float getVndDesconto() {
        return vndDesconto;
    }

    public void setVndDesconto(float vndDesconto) {
        this.vndDesconto = vndDesconto;
    }

    public String getVndStatus() {
        return vndStatus;
    }

    public void setVndStatus(String vndStatus) {
        this.vndStatus = vndStatus;
    }

    public String getVndStatusPagamento() {
        return vndStatusPagamento;
    }

    public void setVndStatusPagamento(String vndStatusPagamento) {
        this.vndStatusPagamento = vndStatusPagamento;
    }
    
    
    
    
    

    @XmlTransient
    public List<ItemVenda> getItemvendaList() {
        return itemvendaList;
    }

    public void setItemvendaList(List<ItemVenda> itemvendaList) {
        this.itemvendaList = itemvendaList;
    }

    @XmlTransient
    public List<Pagamento> getPagamentoList() {
        return pagamentoList;
    }

    public void setPagamentoList(List<Pagamento> pagamentoList) {
        this.pagamentoList = pagamentoList;
    }
    
    public void setListaVenda(List<ItemVenda> listaVenda) {
        this.listaVenda = listaVenda;
        for (ItemVenda iv : listaVenda) {
            iv.setVenda(this);
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vndCodvend != null ? vndCodvend.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Venda)) {
            return false;
        }
        Venda other = (Venda) object;
        if ((this.vndCodvend == null && other.vndCodvend != null) || (this.vndCodvend != null && !this.vndCodvend.equals(other.vndCodvend))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Venda[ vndCodvend=" + vndCodvend + " ]";
    }
    
    public float getValorTotal(){
        float retorno = 0;
          System.out.println("Array IT" + itemvendaList);
        
        if(!itemvendaList.isEmpty()){
          
        for(int i = 0; i<this.itemvendaList.size(); i++){
            float valor = (float) (this.itemvendaList.get(i).getItvQtd() * this.itemvendaList.get(i).getItvValorUni());
            
            retorno = retorno + valor;
            System.out.println("Print Variaveis Venda: "+this.itemvendaList.get(i).getItvQtd() + this.itemvendaList.get(i).getItvValorUni() );
        }
        }
        return retorno - this.vndDesconto;
    }

}