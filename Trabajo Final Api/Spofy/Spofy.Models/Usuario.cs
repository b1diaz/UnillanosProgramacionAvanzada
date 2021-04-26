using Microsoft.AspNetCore.Identity;
using System;
using System.Collections.Generic;
using System.Text;

namespace Spofy.Models
{
    public class Usuario : IdentityUser
    {
        public List<UsuarioLista> UsuarioLista { get; set; }
        //public virtual List<Lista> Listas { get; set; }

    }
}
