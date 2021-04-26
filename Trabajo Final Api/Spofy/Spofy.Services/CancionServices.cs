using Microsoft.EntityFrameworkCore;
using Spofy.DataAccess.Repository;
using Spofy.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Spofy.Services
{
    public class CancionServices
    {
        #region --------- Dependencias ------------
        private readonly IRepository<Cancion> _CancionRepository;

        public CancionServices(IRepository<Cancion> CancionRepository)
        {
            this._CancionRepository = CancionRepository;
        }
        #endregion

        #region --------------- GET -------------------
        /// <summary>
        /// Obtiene una lista de canciones
        /// </summary>
        /// <returns></returns>
        public async Task<List<Cancion>> GetAll()
        {
            var Canciones = await this._CancionRepository.Query()
                .ToListAsync();

            return Canciones;
        }

        /// <summary>
        /// Obtiene una lista de canciones filtradas por nombre o genero de la cancion o nombre del autor
        /// </summary>
        /// <param name="filtro"></param>
        /// <returns></returns>
        public async Task<List<Cancion>> GetAll(string filtro)
        {
            var Canciones = await this._CancionRepository.Query()
                .Include(x => x.Autor)                
                .Where(x => x.Nombre == filtro || x.Autor.Nombre == filtro || x.Genero == filtro)
                .ToListAsync();

            return Canciones;
        }

        #endregion

        #region ----------------- POST -----------------
        /// <summary>
        /// Crea una cancion
        /// <param name="Cancion"></param>
        /// <returns></returns>
        public async Task Create(Cancion Cancion)
        {
            try
            {
                await this._CancionRepository.AddAsync(Cancion);
                this._CancionRepository.SaveChange();
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }
        #endregion

        #region ------------- UPDATE ------------------
        public async Task Update(Cancion Cancion)
        {
            try
            {
                var CancionAnterior = await this._CancionRepository.Query().FirstOrDefaultAsync(x => x.Id == Cancion.Id);

                CancionAnterior.Nombre = Cancion.Nombre;
                this._CancionRepository.Update(CancionAnterior);
                this._CancionRepository.SaveChange();
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }
        #endregion

        #region ---------------- DELETE ------------------
        public async Task Delete(long CancionId)
        {
            var Cancion = await this._CancionRepository.Query().FirstOrDefaultAsync(x => x.Id == CancionId);
            this._CancionRepository.Remove(Cancion);
            this._CancionRepository.SaveChange();

        }
        #endregion
    }
}
