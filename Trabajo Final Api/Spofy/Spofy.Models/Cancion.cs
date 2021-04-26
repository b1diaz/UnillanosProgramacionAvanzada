using System;
using System.Collections.Generic;
using System.Text;
using System.Text.Json.Serialization;

namespace Spofy.Models
{
    public class Cancion
    {
        public long Id { get; set; }
        public string Nombre { get; set; }
        public string Genero { get; set; }
        public long AutorId { get; set; }
        public Autor Autor { get; set; }
        public List<ListaCancion> ListaCancion { get; set; }
        // virtual public List<Lista> Listas { get; set; }
    }
}
