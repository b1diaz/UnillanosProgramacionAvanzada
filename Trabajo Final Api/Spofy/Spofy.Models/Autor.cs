using System;
using System.Collections.Generic;
using System.Text.Json.Serialization;

namespace Spofy.Models
{
    public class Autor
    {
        public long Id { get; set; }
        public string Nombre { get; set; }

        //public virtual List<Cancion> Canciones { get; set; }
    }
}
