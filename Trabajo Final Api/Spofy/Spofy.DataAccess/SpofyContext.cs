using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using Spofy.Models;

namespace Spofy.DataAccess
{
    public class SpofyContext : IdentityDbContext<Usuario>
    {
        public SpofyContext(DbContextOptions<SpofyContext> options) : base(options)
        {

        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);

           // modelBuilder.Entity<Usuario>()
           //     .Ignore(Usuario => Usuario.Listas);
           //
           // modelBuilder.Entity<Lista>()
           //    .Ignore(Lista => Lista.Usuarios)
           //    .Ignore(Lista => Lista.Canciones);
           //
           // modelBuilder.Entity<Cancion>()
           //    .Ignore(Cancion => Cancion.Listas);

            modelBuilder.Entity<UsuarioLista>()
                .HasKey(UsuarioLista => new { UsuarioLista.UsuarioId, UsuarioLista.ListaId });

            modelBuilder.Entity<ListaCancion>()
                .HasKey(ListaCancion => new { ListaCancion.ListaId, ListaCancion.CancionId });
        }

        public DbSet<Autor> Autor { get; set; }
        public DbSet<Cancion> Cancion { get; set; }
        public DbSet<Lista> Lista { get; set; }
        public DbSet<ListaCancion> ListaCancion { get; set; }

    }
}
