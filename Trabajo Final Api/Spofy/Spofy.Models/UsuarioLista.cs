using System;
using System.Collections.Generic;
using System.Text;

namespace Spofy.Models
{
    public class UsuarioLista
    {
        public string UsuarioId { get; set; }
        public Usuario Usuario { get; set; }
        public long ListaId { get; set; }
        public Lista Lista { get; set; }
    }
}
