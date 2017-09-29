/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.baco.gimnasio.ejb;

import co.edu.uniandes.baco.gimnasio.entities.TipoMedidaEntity;
import co.edu.uniandes.baco.gimnasio.entities.TipoMedidaEntity;
import co.edu.uniandes.baco.gimnasio.exceptions.BusinessLogicException;
import co.edu.uniandes.baco.gimnasio.exceptions.NoExisteException;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author t.kavanagh
 */
@Stateless
public class TipoMedidaLogic extends BaseLogic<TipoMedidaEntity>{
     //-----------------------------------
    // MAQUINA
    //-----------------------------------
    @Inject
    private MaquinaLogic logic;
    
    public List<TipoMedidaEntity> findAllTipoMedidaMaquina(Long id) throws BusinessLogicException{
        return logic.find(id).getTipoMedida();
    }
    
    public TipoMedidaEntity findTipoMedidaMaquina(Long idMaquina, Long id) throws BusinessLogicException{
        TipoMedidaEntity aux = new TipoMedidaEntity();
        aux.setId(id);
        List<TipoMedidaEntity> list=logic.find(idMaquina).getTipoMedida();
        int ind=list.indexOf(aux);
        if(ind<0)
            throw new NoExisteException(id);
        return list.get(ind);
    }
    
    public TipoMedidaEntity createTipoMedidaMaquina(Long idMaquina, Long id) throws BusinessLogicException{
        TipoMedidaEntity aux = find(id);
        if(aux==null)
            throw new  NoExisteException(id);
        logic.find(idMaquina).getTipoMedida().add(aux);
        return aux;
    }
    public List<TipoMedidaEntity> createlist(List<TipoMedidaEntity> list) throws BusinessLogicException
    {
     
        for(int i =0;i<list.size();i++)
       {
           create(list.get(i));
        }
        return list;
    }
    public void removeTipoMedidaMaquina(Long idMaquina, Long id) throws BusinessLogicException{
        TipoMedidaEntity aux = new TipoMedidaEntity();
        aux.setId(id);
        List<TipoMedidaEntity> list=logic.find(idMaquina).getTipoMedida();
        int ind=list.indexOf(aux);
        if(ind<0)
            throw new NoExisteException(id);
        list.remove(aux);
    }
}