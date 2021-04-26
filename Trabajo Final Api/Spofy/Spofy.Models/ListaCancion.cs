using System;
using System.Collections.Generic;
using System.Text;

namespace Spofy.Models
{
    public class ListaCancion
    {
        public long ListaId { get; set; }
        public Lista Lista { get; set; }
        public long CancionId { get; set; }
        public Cancion Cancion { get; set; }
    }
}
