/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;

/**
 *
 * @author rodrigo
 */
public interface Consultas <ClaseGenerica> {
        public int create(ClaseGenerica c);
        public boolean delete(Object key);
        public boolean update(ClaseGenerica c);
        public ClaseGenerica selectOne(Object key);
        public List<ClaseGenerica> selectAll();
        public List<ClaseGenerica> selectRelated(Object key);
}