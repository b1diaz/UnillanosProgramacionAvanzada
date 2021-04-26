using System;
using System.Collections.Generic;

namespace Spofy.Models
{
    public class Lista
    {
        public long Id { get; set; }
        public string Nombre { get; set; }
        public List<ListaCancion> ListaCancion { get; set; }
        public virtual List<Cancion> Canciones { get; set; }
        public List<UsuarioLista> UsuarioLista { get; set; }
        // public virtual List<Usuario> Usuarios { get; set; }

    }
}
