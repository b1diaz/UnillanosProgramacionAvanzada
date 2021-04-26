using Microsoft.EntityFrameworkCore;
using Spofy.DataAccess.Repository;
using Spofy.Models;
using System;
using System.Threading.Tasks;

namespace Spofy.Services
{
    public class UsuarioServices
    {
        #region --------- Dependencias ------------
        private readonly IRepository<Usuario> _UsuarioRepository;

        public UsuarioServices(IRepository<Usuario> UsuarioRepository)
        {
            this._UsuarioRepository = UsuarioRepository;
        }
        #endregion


        #region ------------------ UPDATE ---------------
        /// <summary>
        /// Agrega una lista a la listas de reproducciones del usuario
        /// </summary>
        /// <param name="UsuarioId"></param>
        /// <param name="ListaId"></param>
        /// <returns></returns>
        public async Task AddLista(string UsuarioId, long ListaId)
        {
            try
            {
                var Usuario = await this._UsuarioRepository.Query().FirstOrDefaultAsync(x => x.Id == UsuarioId);

                var UsuarioLista = new UsuarioLista()
                {
                    UsuarioId= UsuarioId,
                    ListaId = ListaId
                };

                Usuario.UsuarioLista.Add(UsuarioLista);

                this._UsuarioRepository.Update(Usuario);
                this._UsuarioRepository.SaveChange();
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }


        /// <summary>
        /// Elimina una lista de las listas del reproduccion del usuario
        /// </summary>
        /// <param name="UsuarioId"></param>
        /// <param name="ListaId"></param>
        /// <returns></returns>
        public async Task DeleteLista(string UsuarioId, long ListaId)
        {
            try
            {
                var Usuario = await this._UsuarioRepository.Query().FirstOrDefaultAsync(x => x.Id == UsuarioId);

                var UsuarioLista = new UsuarioLista()
                {
                    UsuarioId = UsuarioId,
                    ListaId = ListaId
                };

                Usuario.UsuarioLista.Remove(UsuarioLista);

                this._UsuarioRepository.Update(Usuario);
                this._UsuarioRepository.SaveChange();
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }


        #endregion

    }
}
