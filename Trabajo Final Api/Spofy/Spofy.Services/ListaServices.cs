using Microsoft.EntityFrameworkCore;
using Spofy.DataAccess.Repository;
using Spofy.Models;
using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;

namespace Spofy.Services
{
    public class ListaServices
    {
        #region --------- Dependencias ------------
        private readonly IRepository<Lista> _ListaRepository;

        public ListaServices(IRepository<Lista> ListaRepository)
        {
            this._ListaRepository = ListaRepository;
        }
        #endregion

        #region --------------- GET -------------------
        /// <summary>
        /// Obtiene una lista de Listas de reproduccion
        /// </summary>
        /// <returns></returns>
        public async Task<List<Lista>> GetAll()
        {
            var Listas = await this._ListaRepository.Query()
                .ToListAsync();

            return Listas;
        }
        #endregion

        #region ----------------- POST -----------------
        /// <summary>
        /// Crea una Lista
        /// <param name="Cancion"></param>
        /// <returns></returns>
        public async Task Create(Lista Lista)
        {
            try
            {
                await this._ListaRepository.AddAsync(Lista);
                this._ListaRepository.SaveChange();
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }
        #endregion

        #region ------------------ UPDATE ---------------
        /// <summary>
        /// Crea una Lista
        /// <param name="Lista"></param>
        /// <returns></returns>
        public async Task Update(Lista Lista)
        {
            try
            {
                var listaAntigua = await this._ListaRepository.Query().FirstOrDefaultAsync(x => x.Id == Lista.Id);


                listaAntigua.ListaCancion = Lista.ListaCancion;
                listaAntigua.Nombre = Lista.Nombre;
                listaAntigua.UsuarioLista = Lista.UsuarioLista;

                this._ListaRepository.Update(listaAntigua);
                this._ListaRepository.SaveChange();
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }


        /// <summary>
        /// Agrega una cancion a un lista
        /// </summary>
        /// <param name="ListaId"></param>
        /// <param name="CancionId"></param>
        /// <returns></returns>
        public async Task AddCancion(long ListaId, long CancionId)
        {
            try
            {
                var lista = await this._ListaRepository.Query().FirstOrDefaultAsync(x => x.Id == ListaId);

                var ListaCancion = new ListaCancion()
                {
                    CancionId = CancionId,
                    ListaId = ListaId
                };

                lista.ListaCancion.Add(ListaCancion);

                this._ListaRepository.Update(lista);
                this._ListaRepository.SaveChange();
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }


        /// <summary>
        /// Elimina una cancion de un lista
        /// </summary>
        /// <param name="ListaId"></param>
        /// <param name="CancionId"></param>
        /// <returns></returns>
        public async Task DeleteCancion(long ListaId, long CancionId)
        {
            try
            {
                var lista = await this._ListaRepository.Query().FirstOrDefaultAsync(x => x.Id == ListaId);

                var ListaCancion = new ListaCancion()
                {
                    CancionId = CancionId,
                    ListaId = ListaId
                };

                lista.ListaCancion.Remove(ListaCancion);

                this._ListaRepository.Update(lista);
                this._ListaRepository.SaveChange();
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        #endregion

        #region -------------- DELETE -----------------
        /// <summary>
        /// Elimina una lista
        /// <param name="ListaId"></param>
        /// <returns></returns>
        public async Task Delete(long ListaId)
        {
            try
            {
                var lista = await this._ListaRepository.Query().FirstOrDefaultAsync(x => x.Id == ListaId);
                this._ListaRepository.Remove(lista);

                this._ListaRepository.SaveChange();
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }
        #endregion

    }
}
